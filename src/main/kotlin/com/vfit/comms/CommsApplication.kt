package com.vfit.comms

import com.vfit.comms.config.AMQPConfig
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

@EnableRabbit
@SpringBootApplication
class CommsApplication

fun main(args: Array<String>) {

	/*
	// set up the connection
	val connectionFactory = CachingConnectionFactory("bloodhound.rmq.cloudamqp.com")
	connectionFactory.setUsername("paelyzfv")
	connectionFactory.setPassword("XSpPlzaSMsb9uY_Ht4lpHFSfdOq36mk1")
	connectionFactory.virtualHost = "paelyzfv"

	//Recommended settings
	connectionFactory.setRequestedHeartBeat(30)
	connectionFactory.setConnectionTimeout(30000)

	//Set up queue, exchanges and bindings
	val admin = RabbitAdmin(connectionFactory)
	val queue = Queue("myQueue")
	admin.declareQueue(queue)

	val exchange = TopicExchange("myEExchange")
	admin.declareExchange(exchange)
	admin.declareBinding(
			BindingBuilder.bind(queue).to(exchange).with("foo.*"))

	//Set up the listener
	val container = SimpleMessageListenerContainer(connectionFactory)
	val listener: Any = object : Any() {
		fun handleMessage(foo: String?) {
			println("got message")
			println(foo)
		}
	}
	*/
	/*
	//Send a message
	val adapter = MessageListenerAdapter(listener)
	container.messageListener = adapter
	container.setQueueNames("myQueue")
	container.start()

	val template = RabbitTemplate(connectionFactory)
	template.convertAndSend("myExchange", "foo.bar", "Hello CloudAMQP!")
	try {
		Thread.sleep(1000)
	} catch (e: InterruptedException) {
		Thread.currentThread().interrupt()
	}
	container.stop()
	*/



	runApplication<CommsApplication>(*args)
}
