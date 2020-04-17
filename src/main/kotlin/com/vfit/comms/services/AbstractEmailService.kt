package com.vfit.comms.services

import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import javax.mail.Message
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

abstract class AbstractEmailService(
        val mailSender: JavaMailSender
) {

    @Value("\${email.noreply}")
    lateinit var noReplyEmail: String

    protected fun baseMessageBuilder(subject: String, to: String, from: String, body: String): MimeMessage {
        val message = mailSender.createMimeMessage()
        message.addRecipient(Message.RecipientType.TO, InternetAddress(to))
        message.subject = subject
        message.setFrom(from)
        message.replyTo = arrayOf(InternetAddress(from))
        message.setContent(body, "text/html")
        return message
    }

    protected fun sendEmail(subject: String, to: String, from: String, body: String) {
        try {
            var msg = baseMessageBuilder(subject, to, noReplyEmail, body)
            mailSender.send(msg)
        } catch (e: Exception) {

        }
    }

}