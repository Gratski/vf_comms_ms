package com.vfit.comms.listeners.welcome

import com.vfit.comms.services.StudentEmailService
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Controller

/**
 * Controller that handles messages from Students Welcome Queue
 */
@Controller
@RabbitListener(queues = ["\${queue.welcome.students}"], containerFactory = "inputListenerContainerFactory")
class StudentWelcomeListener(
        val emailService: StudentEmailService
) {

    @RabbitHandler
    fun receive(fileBody: LinkedHashMap<Any, Any>) {
        val to = fileBody["to"] as String
        val languageTag = fileBody["languageTag"] as String
        val username = fileBody["username"] as String
        emailService.sendStudentWelcomeEmail(to, languageTag, username)
    }

}