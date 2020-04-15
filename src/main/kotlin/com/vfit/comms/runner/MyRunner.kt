package com.vfit.comms.runner

import com.google.gson.Gson
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageProperties
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.*

@Component
class MyRunner(val template: RabbitTemplate): CommandLineRunner {


    override fun run(vararg args: String?) {
        val props = MessageProperties()
        props.contentType = "json"
        val msg = Message(Gson().toJson(com.vfit.comms.Message(id = 1, body = "", languageTag = "en_US")).toByteArray(), props)
        template.encoding = "application/json"
        template.convertAndSend("comms.email.support", msg)
    }
}