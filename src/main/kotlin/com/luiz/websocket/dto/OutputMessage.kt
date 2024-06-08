package com.luiz.websocket.dto

data class OutputMessage(
    val from: String? = null,
    val text: String? = null,
    val time: String
)