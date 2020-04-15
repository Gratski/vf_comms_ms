package com.vfit.comms.runner

import com.google.gson.Gson
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageProperties
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class MyRunner(val template: RabbitTemplate): CommandLineRunner {


    override fun run(vararg args: String?) {
        val props = MessageProperties()
        props.contentType = "json"
        val msg = Message(Gson().toJson(com.vfit.comms.Message(ticketId = "TIFSDJKGFHS", id = 1, to = "rodrigues.at.work@gmail.com", languageTag = "pt-PT", username = "Joao Rodrigues")).toByteArray(), props)
        template.encoding = "application/json"
        template.convertAndSend("comms.email.support.professors", msg)
    }
}