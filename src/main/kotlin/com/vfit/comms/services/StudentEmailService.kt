package com.vfit.comms.services

import com.vfit.comms.localization.MessageCodes
import com.vfit.comms.localization.Translator
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class StudentEmailService(mailSender: JavaMailSender): AbstractEmailService(mailSender) {

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

}