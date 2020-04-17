package com.vfit.comms.listeners.support

import com.vfit.comms.services.ProfessorEmailService
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Controller

/**
 * Controller that handles messages from Professors Support Queue
 */
@Controller
@RabbitListener(queues = ["\${queue.support.professors}"], containerFactory = "inputListenerContainerFactory")
class ProfessorSupportListener(
        val emailService: ProfessorEmailService
) {

    @RabbitHandler
    fun receive(fileBody: LinkedHashMap<Any, Any>) {
        val to = fileBody["to"] as String
        val languageTag = fileBody["languageTag"] as String
        val username = fileBody["username"] as String
        val ticketID = fileBody["ticketId"] as String
        emailService.sendProfessorSupportEmail(to, languageTag, username, ticketID)
    }

}