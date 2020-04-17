package com.vfit.comms.listeners.invitation

import com.vfit.comms.services.ProfessorEmailService
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Controller

/**
 * Controller that handles messages from Professors Support Queue
 */
@Controller
@RabbitListener(queues = ["\${queue.invitation.professors}"], containerFactory = "inputListenerContainerFactory")
class ProfessorInvitationListener(
        val emailService: ProfessorEmailService
) {

    @RabbitHandler
    fun receive(fileBody: LinkedHashMap<Any, Any>) {
        val to = fileBody["to"] as Array<String>
        val languageTag = fileBody["languageTag"] as String
        val username = fileBody["fromUsername"] as String
        emailService.sendInvitationFromProfessor(to, languageTag, username)
    }

}