package com.vfit.comms

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.boot.CommandLineRunner


class MessageListenerRunner(
        private val connectionFactory: CachingConnectionFactory
        ): CommandLineRunner {

    override fun run(vararg args: String?) {
        val container = SimpleMessageListenerContainer(connectionFactory)
        val listener: Any = object : Any() {
            fun handleMessage(foo: String?) {
                println(foo)
            }
        }
        println("the message listener is set")
    }

}