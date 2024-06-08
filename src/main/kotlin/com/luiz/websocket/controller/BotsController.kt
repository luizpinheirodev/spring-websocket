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
class BotsController {
    @MessageMapping("/chatwithbots")
    @SendTo("/topic/pushmessages")
    @Throws(Exception::class)
    fun send(message: Message): OutputMessage {
        val time = SimpleDateFormat("HH:mm:ss").format(Date())
        LOGGER.info("Sending bot message $message at $time")
        return OutputMessage(message.from, message.text, time)
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(BotsController::class.java)
    }
}