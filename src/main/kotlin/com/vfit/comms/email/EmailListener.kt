package com.vfit.comms.email

import com.vfit.comms.Message
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Controller

@Controller
@RabbitListener(queues = ["comms.email.support"], containerFactory = "inputListenerContainerFactory")
class EmailListener(val emailService: EmailService) {

    @RabbitHandler
    fun receive(fileBody: Message) {
        println("Mensagem Nome")
    }

    @RabbitHandler
    fun receive(fileBody: LinkedHashMap<Any, Any>) {
        emailService.sendEmailWelcomeEmail("email", "body", "fdfsfd-FDSFSD")
        println("Mensagem Nome")
    }

}