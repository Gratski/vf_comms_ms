package com.vfit.comms.localization

import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.stereotype.Component
import java.util.*

@Component
class Translator(
        val localizationService: LocalizationService,
        val messageSource: ResourceBundleMessageSource) {

    companion object {
        private var messageSource: ResourceBundleMessageSource? = null
        private var localizationService: LocalizationService? = null

        fun toLocale(msgCode: String, languageTag: String? = null): String {
            var locale = localizationService!!.getLocale(languageTag)
            if(locale.toLanguageTag() == null)
                locale = Locale.ENGLISH

            return messageSource!!.getMessage(msgCode, null, locale)
        }

        fun toLocale(msgCode: String, args: Array<String>): String {
            val locale = LocaleContextHolder.getLocale()
            return messageSource!!.getMessage(msgCode, args, locale)
        }
    }

    init {
        Companion.localizationService = localizationService
        Companion.messageSource = messageSource
    }
}