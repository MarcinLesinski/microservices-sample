package com.example.client.hystrix

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty
import kotlin.random.Random
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Service
class HystrixSampleClient(
    @LoadBalanced
    private val rest: RestTemplate
) {
    fun getPort(): Int = rest.getForObject("http://numbers-provider/info/port")

    @HystrixCommand(fallbackMethod = "webDataFallback")
    fun getWebData(decorator: String): String? {
        val response = rest.getForObject(
                "http://numbers-provider/data/valid",
                String::class.java
            )
        return decorator + response + decorator
    }

    private fun webDataFallback(decorator: String): String {
        return decorator + "fallback" + decorator
    }

    @HystrixCommand(
        fallbackMethod = "timeoutDataFallback",
        commandProperties = [
            HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
        ]
    )
    fun timeoutData(): String {
        Thread.sleep(600)
        return "valid data"
    }

    private fun timeoutDataFallback(): String = "timeout fallback data"


    private var everyFifthCounter = 0
    @HystrixCommand(
        fallbackMethod = "everyFifthFallback",
        commandProperties = [
            HystrixProperty(value = "10000", name ="metrics.rollingStats.timeInMilliseconds"),
            HystrixProperty(value = "5", name ="circuitBreaker.requestVolumeThreshold"),
        ]
    )
    fun everyFifth(): String {
        everyFifthCounter++
        val success = everyFifthCounter.mod(5) == 0
        println("every5th $success - $everyFifthCounter" )
        if (success.not()) throw Exception()
        return "valid data"
    }
    private fun everyFifthFallback(): String {
        println("every5th fallback - $everyFifthCounter" )
        return "fallback"
    }

    @HystrixCommand(
        fallbackMethod = "getCachedData",
    )
    fun getData(): String {
        val standardFlow = Random.nextBoolean()
        if (standardFlow) return "fresh data"
        else throw RandomTriggeredException()
    }

    private fun getCachedData(): String = "cached data"
}
class RandomTriggeredException: Exception()
