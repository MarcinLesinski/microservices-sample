package com.example.server.web

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.boot.web.context.WebServerInitializedEvent
import org.springframework.context.ApplicationListener
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/info")
internal class InfoController(
): ApplicationListener<WebServerInitializedEvent> {
    private var port = 0

    @GetMapping("/port")
    fun port(): Int {
        return  port
    }

    override fun onApplicationEvent(event: WebServerInitializedEvent) {
        val port = event.webServer.port
        this.port = port
    }
}
