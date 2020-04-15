package com.vfit.comms.listeners

import com.vfit.comms.services.EmailService
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Controller

/**
 * Controller that handles messages from Professors Welcome Queue
 */
@Controller
@RabbitListener(queues = ["\${queue.welcome.professors}"], containerFactory = "inputListenerContainerFactory")
class ProfessorWelcomeListener(val emailService: EmailService) {

    @RabbitHandler
    fun receive(fileBody: LinkedHashMap<Any, Any>) {
        val to = fileBody["to"] as String
        val languageTag = fileBody["languageTag"] as String
        val username = fileBody["username"] as String
        emailService.sendProfessorWelcomeEmail(to, languageTag, username)
    }

}