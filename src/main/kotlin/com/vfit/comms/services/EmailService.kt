package com.vfit.comms.services

import com.vfit.comms.localization.MessageCodes
import com.vfit.comms.localization.Translator
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import javax.mail.Message
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


@Service
class EmailService(

        val mailSender: JavaMailSender) {

    @Value("\${email.noreply}")
    lateinit var noReplyEmail: String;

    fun sendProfessorWelcomeEmail(to: String, languageTag: String, username: String) {
        val subject = Translator.toLocale(MessageCodes.WELCOME_PROFESSOR_EMAIL_SUBJECT, languageTag)
        val body = String.format(Translator.toLocale(MessageCodes.WELCOME_PROFESSOR_EMAIL_BODY, languageTag), username, username)
        val msg = baseMessageBuilder(subject, to, noReplyEmail, body)
        try {
            mailSender.send(msg)
        } catch (e: Exception) {

        }
    }

    fun sendProfessorSupportEmail(to: String, languageTag: String, username: String, ticketID: String) {
        val subject = Translator.toLocale(MessageCodes.SUPPORT_PROFESSOR_EMAIL_SUBJECT, languageTag)
        val body = String.format(Translator.toLocale(MessageCodes.SUPPORT_PROFESSOR_EMAIL_BODY, languageTag), username, username)
        val msg = baseMessageBuilder(subject, to, noReplyEmail, body)
        try {
            mailSender.send(msg)
        } catch (e: Exception) {

        }
    }

    fun sendStudentWelcomeEmail(to: String, languageTag: String, username: String) {
        val subject = Translator.toLocale(MessageCodes.WELCOME_STUDENT_EMAIL_SUBJECT, languageTag)
        val body = String.format(Translator.toLocale(MessageCodes.WELCOME_STUDENT_EMAIL_BODY, languageTag), username, username)
        val msg = baseMessageBuilder(subject, to, noReplyEmail, body)
        try {
            mailSender.send(msg)
        } catch (e: Exception) {

        }
    }

    fun sendStudentSupportEmail(to: String, languageTag: String, username: String, ticketID: String) {
        val subject = Translator.toLocale(MessageCodes.SUPPORT_STUDENT_EMAIL_SUBJECT, languageTag)
        val body = String.format(Translator.toLocale(MessageCodes.SUPPORT_STUDENT_EMAIL_BODY, languageTag), username, username)
        val msg = baseMessageBuilder(subject, to, noReplyEmail, body)
        try {
            mailSender.send(msg)
        } catch (e: Exception) {

        }
    }

    private fun baseMessageBuilder(subject: String, to: String, from: String, body: String): MimeMessage {
        val message = mailSender.createMimeMessage()
        message.addRecipient(Message.RecipientType.TO, InternetAddress(to))
        message.subject = subject
        message.setFrom(from)
        message.replyTo = arrayOf(InternetAddress(from))
        message.setContent(body, "text/html")
        return message
    }

}