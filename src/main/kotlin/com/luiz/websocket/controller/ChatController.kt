package com.luiz.websocket.controller

import com.luiz.websocket.dto.Message
import com.luiz.websocket.dto.OutputMessage
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import java.text.SimpleDateFormat
import java.util.*


@Controller
class ChatController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    @Throws(Exception::class)
    fun send(message: Message): OutputMessage {
        val time = SimpleDateFormat("HH:mm").format(Date())
        LOGGER.info("Sending chat message $message at $time")
        return OutputMessage(message.from, message.text, time)
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(ChatController::class.java)
    }
}