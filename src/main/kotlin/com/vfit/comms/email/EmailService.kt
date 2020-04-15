package com.vfit.comms.email

import com.vfit.comms.localization.MessageCodes
import com.vfit.comms.localization.Translator
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service


@Service
class EmailService(

        val mailSender: JavaMailSender) {

    fun sendEmailWelcomeEmail(to: String, body: String, languageTag: String) {
        val body = Translator.toLocale(MessageCodes.WELCOME, languageTag)
        println("SENDING EMAIL")
        val message = SimpleMailMessage()
        message.setTo("rodrigues.at.work@gmail.com")
        message.setSubject("Welcome to VFit")
        message.setText("Benvindo")
        //mailSender.send(message)
    }

    fun sendEmailSupportConfirmationEmail(to: String, ticketId: String, supportType: String) {
        print("Send support confirmation email")
    }

}