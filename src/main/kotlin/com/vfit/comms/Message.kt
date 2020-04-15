package com.vfit.comms

data class Message (
        val id: Int,
        val to: String,
        val languageTag: String,
        val username: String,
        val ticketId: String? = null
)