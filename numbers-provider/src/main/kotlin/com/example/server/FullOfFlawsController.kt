package com.example.server

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@RequestMapping
interface FullOfFlawsController {
    @GetMapping("/data/valid")
    fun getValidData(): String

    @GetMapping("/data/delayed")
    fun getDelayedData(@RequestParam("timeInMs") timeInMs: Long): String

    @GetMapping("/data/every-fifth")
    fun getValidEveryFifth(): String
}
