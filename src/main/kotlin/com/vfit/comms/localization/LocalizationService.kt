package com.vfit.comms.localization

import org.springframework.stereotype.Service
import java.util.*

@Service
class LocalizationService {

    val LOCALES: List<Locale> = listOf(
            Locale("en"),
            Locale("pt"),
            Locale("es"),
            Locale("fr")
    )

    /**
     * Gets a locale based on a Language Tag
     */
    fun getLocale(languageTag: String?): Locale =
            if (languageTag == null || languageTag.isBlank())
                Locale.getDefault()
            else Locale.forLanguageTag(languageTag)

}