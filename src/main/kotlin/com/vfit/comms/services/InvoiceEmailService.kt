package com.vfit.comms.services

import com.vfit.comms.localization.MessageCodes
import com.vfit.comms.localization.Translator
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class InvoiceEmailService(
        mailSender: JavaMailSender
): AbstractEmailService(mailSender) {

    @Value("\${email.billing}")
    lateinit var billingEmail: String

    fun handleInvoiceReceived(to: String, username: String,
                              invoiceId: String, docUrl: String, languageTag: String){

        // send email to tecnical team
        var subject = Translator.toLocale(MessageCodes.INVOICE_RECEIVED_SUBJECT, languageTag)
        var body = String.format(Translator.toLocale(MessageCodes.INVOICE_RECEIVED_BODY, languageTag), username)
        sendEmail(subject, to, noReplyEmail, body)

        // send confirmation email to professor
        subject = "Payment Request"
        body = String.format("A new invoice has arrived from %s. <br>Doc url: %s<br> InvoiceId: %s",  username, docUrl, invoiceId)
        sendEmail(subject, billingEmail, noReplyEmail, body)

    }

}