package com.luiz.websocket.service

import com.github.javafaker.Faker
import com.luiz.websocket.dto.OutputMessage
import org.slf4j.LoggerFactory
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*


@Service
class ScheduledPushMessages(private val simpMessagingTemplate: SimpMessagingTemplate) {

    private val faker: Faker = Faker()

    @Scheduled(fixedRate = 5000)
    fun sendMessage() {
        val time = SimpleDateFormat("HH:mm:ss").format(Date())
        LOGGER.info("Sending scheduled message at $time")
        simpMessagingTemplate.convertAndSend(
            "/topic/pushmessages",
            OutputMessage("Chuck Norris", faker.chuckNorris().fact(), time)
        )
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(this::class.java)
    }
}