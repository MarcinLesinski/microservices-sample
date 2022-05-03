package com.example.server

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
internal class FullOfFlawsControllerImpl: FullOfFlawsController {

    override fun getDelayedData(@RequestParam("timeInMs") timeInMs: Long ): String {
        Thread.sleep(timeInMs)
        return "valid data"
    }

    private var everyFifthCounter = 0
    override fun getValidEveryFifth(): String {
        everyFifthCounter++
        if (everyFifthCounter.mod(5) == 0) throw Exception()
        return "valid data"
    }

    override fun getValidData(): String {
        return "valid data"
    }
}

