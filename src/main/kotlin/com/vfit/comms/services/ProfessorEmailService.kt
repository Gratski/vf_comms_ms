package com.vfit.comms.services

import com.vfit.comms.localization.MessageCodes
import com.vfit.comms.localization.Translator
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class ProfessorEmailService(mailSender: JavaMailSender): AbstractEmailService(mailSender) {

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

    fun sendInvitationFromProfessor(to: Array<String>, languageTag: String, fromUsername: String) {

    }

}