package com.example.client

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order

@SpringBootApplication
//@EnableFeignClients // moved to Feign Configuration
@EnableHystrix
/*
    prioritize hystrix aop over db transactions
    @EnableTransactionManagement( order=Ordered.LOWEST_PRECEDENCE, mode=AdviceMode.ASPECTJ)
 */
@EnableHystrixDashboard
class ClientApplication{
    @Bean
    @Primary
    @Order(value = Ordered.HIGHEST_PRECEDENCE)
    fun hystrixAspect(): HystrixCommandAspect? {
        return HystrixCommandAspect()
    }

}
fun main(args: Array<String>) {
    runApplication<ClientApplication>(*args)
}

