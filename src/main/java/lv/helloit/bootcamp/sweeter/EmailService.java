package lv.helloit.bootcamp.sweeter;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final Client client;

    public EmailService(@Qualifier("sparkPostClient") Client client) {
        this.client = client;
    }


    public void sendEmail(String emailTo) throws SparkPostException {
        client.sendMessage(
                "you@yourdomain.com",
                "to@sparkpost.com",
                "The subject of the message",
                "The text part of the email",
                "<b>The HTML part of the email</b>");;

    }

    public void sendNewUserEmail(String userEmail) throws SparkPostException {
        String API_KEY = "339165f89dac945c6ad84fe63d2e8e89d505f7f8";
        Client client = new Client(API_KEY);

        client.sendMessage(
                "sweeter@mmm.id.lv",
                userEmail,
                "Greetings from sweeter",
                "You successfully registered",
                "<b>You successfully registered</b>");

    }
}

