package com.vfit.comms.config

import com.vfit.comms.Message
import org.springframework.amqp.core.AmqpAdmin
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.DefaultClassMapper
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class AMQPConfig {

    protected val helloWorldQueueName = "hello.world.queue"

    @Bean
    fun connectionFactory(): ConnectionFactory {
        val connectionFactory = CachingConnectionFactory("bloodhound.rmq.cloudamqp.com")
        connectionFactory.username = "paelyzfv"
        connectionFactory.virtualHost = "paelyzfv"
        connectionFactory.setPassword("XSpPlzaSMsb9uY_Ht4lpHFSfdOq36mk1")
        connectionFactory.setRequestedHeartBeat(30);
        connectionFactory.setConnectionTimeout(30000);
        return connectionFactory
    }

    @Bean
    fun amqpAdmin(): AmqpAdmin {
        return RabbitAdmin(connectionFactory())
    }

    @Bean
    fun rabbitTemplate(): RabbitTemplate {
        val template = RabbitTemplate(connectionFactory())
        template.routingKey = this.helloWorldQueueName
        template.setDefaultReceiveQueue(this.helloWorldQueueName)
        return template
    }

    @Bean
    fun inputListenerContainerFactory(): SimpleRabbitListenerContainerFactory {
        val factory = SimpleRabbitListenerContainerFactory()
        factory.setConnectionFactory(connectionFactory())
        factory.setConcurrentConsumers(2)

        factory.setMaxConcurrentConsumers(10)
        factory.setMessageConverter(listnerMessageConverter())
        return factory
    }

    @Bean
    fun listnerMessageConverter(): Jackson2JsonMessageConverter {
        val converter = Jackson2JsonMessageConverter()

        val classMapper = DefaultClassMapper()
        classMapper.setDefaultType(LinkedHashMap::class.java)
        val idClassMapping: MutableMap<String, Class<*>> = HashMap()
        idClassMapping["com.vfit.comms.Message"] = Message::class.java
        classMapper.setIdClassMapping(idClassMapping)
        converter.setClassMapper(classMapper)

        return converter
    }

}