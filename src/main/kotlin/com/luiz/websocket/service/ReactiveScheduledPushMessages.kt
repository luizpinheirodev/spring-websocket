package com.luiz.websocket.service

import com.github.javafaker.Faker
import com.luiz.websocket.dto.OutputMessage
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.*


@Service
class ReactiveScheduledPushMessages(private val simpMessagingTemplate: SimpMessagingTemplate) : InitializingBean {
    private val faker = Faker()

    @Throws(Exception::class)
    override fun afterPropertiesSet() {
        LOGGER.info("Sending flux interval message")
        Flux.interval(Duration.ofSeconds(4L))
            .map { n: Long? ->
                OutputMessage(
                    faker.gameOfThrones().character(), faker.gameOfThrones().quote(),
                    SimpleDateFormat("HH:mm:ss").format(Date())
                )
            }
            .subscribe { message: OutputMessage? ->
                simpMessagingTemplate.convertAndSend(
                    "/topic/pushmessages",
                    message!!
                )
            }
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(this::class.java)
    }
}