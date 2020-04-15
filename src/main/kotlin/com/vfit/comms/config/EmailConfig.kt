package com.vfit.comms.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.*


@Configuration
class EmailConfig {

    @Bean
    fun getJavaMailSender(): JavaMailSender? {
        val mailSender = JavaMailSenderImpl()
        mailSender.host = "smtp.zoho.eu"
        mailSender.port = 587
        mailSender.username = "noreply@vfit.app"
        mailSender.password = "FAWWVABWw9YyK4upathq"
        val props: Properties = mailSender.javaMailProperties
        props["mail.transport.protocol"] = "smtp"
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.starttls.enable"] = "true"
        props["mail.debug"] = "true"
        return mailSender
    }

}