package com.vfit.comms.listeners.auth

import com.vfit.comms.services.AuthEmailService
import com.vfit.comms.services.ProfessorEmailService
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Controller

/**
 * Controller that handles messages from Password Recovery Queue
 */
@Controller
@RabbitListener(queues = ["\${queue.auth.passrecovery}"], containerFactory = "inputListenerContainerFactory")
class PasswordRecoveryListener(
        val emailService: AuthEmailService
) {

    @RabbitHandler
    fun receive(fileBody: LinkedHashMap<Any, Any>) {
        val to = fileBody["to"] as String
        val languageTag = fileBody["languageTag"] as String
        val username = fileBody["username"] as String
        val token = fileBody["token"] as String
        emailService.sendPasswordRecoveryEmail(to, username, token, languageTag)
    }

}