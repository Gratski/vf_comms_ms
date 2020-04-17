package com.vfit.comms.listeners.invoice

import com.vfit.comms.services.InvoiceEmailService
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Controller

/**
 * Controller that handles messages from Invoice Queue
 */
@Controller
@RabbitListener(queues = ["\${queue.invoice.received}"], containerFactory = "inputListenerContainerFactory")
class InvoiceListener(
        val emailService: InvoiceEmailService
) {

    @RabbitHandler
    fun receive(fileBody: LinkedHashMap<Any, Any>) {
        val to = fileBody["to"] as String
        val languageTag = fileBody["languageTag"] as String
        val username = fileBody["username"] as String
        val docUrl = fileBody["docUrl"] as String
        val invoiceId = fileBody["invoiceId"] as String
        emailService.handleInvoiceReceived(
                to = to,
                username = username,
                languageTag = languageTag,
                invoiceId = invoiceId,
                docUrl = docUrl
                )
    }

}