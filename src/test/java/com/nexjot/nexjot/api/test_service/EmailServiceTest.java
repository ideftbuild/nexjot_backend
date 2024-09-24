//package com.example.emailservice;
//
//import com.icegreen.greenmail.configuration.GreenMailConfiguration;
//import com.icegreen.greenmail.junit5.GreenMailExtension;
//import com.icegreen.greenmail.util.ServerSetupTest;
//import com.nexjot.nexjot.api.service.EmailService;
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.RegisterExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@TestPropertySource(properties = {
//        "spring.mail.host=127.0.0.1",
//        "spring.mail.port=3025",
//        "spring.mail.username=test@localhost",
//        "spring.mail.password=password"
//})
//class EmailServiceIntegrationTest {
//
//    @RegisterExtension
//    static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP)
//            .withConfiguration(GreenMailConfiguration.aConfig().withUser("test@localhost", "password"));
//
//    @Autowired
//    private EmailService emailService;
//
//    @Test
//    void testSendEmail() throws MessagingException {
//        // Arrange
//        String to = "recipient@example.com";
//        String subject = "Integration Test Subject";
//        String body = "This is an integration test email body.";
//
//        // Act
//        emailService.sendEmail(to, subject, body);
//
//        // Assert
//        MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
//        assertThat(receivedMessages).hasSize(1);
//        MimeMessage receivedMessage = receivedMessages[0];
//        assertThat(receivedMessage.getSubject()).isEqualTo(subject);
//        assertThat(receivedMessage.getAllRecipients()[0].toString()).isEqualTo(to);
//        assertThat(receivedMessage.getContent().toString().trim()).isEqualTo(body);
//    }
//}