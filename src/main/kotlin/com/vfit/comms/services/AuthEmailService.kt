package com.vfit.comms.services

import com.vfit.comms.localization.MessageCodes
import com.vfit.comms.localization.Translator
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class AuthEmailService(mailSender: JavaMailSender): AbstractEmailService(mailSender) {

    fun sendPasswordRecoveryEmail(to: String, username: String, token: String, languageTag: String) {
        val subject = Translator.toLocale(MessageCodes.AUTH_PASSWORD_RECOVERY_SUBJECT, languageTag)
        val body = String.format(Translator.toLocale(MessageCodes.AUTH_PASSWORD_RECOVERY_BODY, languageTag), token)
        val msg = baseMessageBuilder(subject, to, noReplyEmail, body)
        try {
            mailSender.send(msg)
        } catch (e: Exception) {

        }
    }

}