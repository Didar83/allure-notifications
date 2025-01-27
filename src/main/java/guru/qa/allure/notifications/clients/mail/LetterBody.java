package guru.qa.allure.notifications.clients.mail;

import guru.qa.allure.notifications.exceptions.MessageBuildException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class LetterBody {
    private final MimeMultipart multipart = new MimeMultipart("related");

    public void addText(final String body) throws MessageBuildException {
        BodyPart textBody = new MimeBodyPart();
        try {
            textBody.setContent(body, "text/html; charset=UTF-8");
            multipart.addBodyPart(textBody);
        } catch (MessagingException e) {
            throw new MessageBuildException("Unable to create text body!", e);
        }
    }

    public void addImage(final byte[] image) throws MessageBuildException {
        BodyPart imageBody = new MimeBodyPart();
        DataSource dataSource = new ByteArrayDataSource(image, "image/png");
        try {
            imageBody.setDataHandler(new DataHandler(dataSource));
            imageBody.setHeader("Content-ID", "<image>");
            multipart.addBodyPart(imageBody);
        } catch (MessagingException e) {
            throw new MessageBuildException("Unable to create image body!", e);
        }
    }

    public MimeMultipart getMultipart() {
        return multipart;
    }
}
