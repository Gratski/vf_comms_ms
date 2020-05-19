package com.vfit.comms

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableRabbit
@SpringBootApplication
class CommsApplication

fun main(args: Array<String>) {
	runApplication<CommsApplication>(*args)
}
