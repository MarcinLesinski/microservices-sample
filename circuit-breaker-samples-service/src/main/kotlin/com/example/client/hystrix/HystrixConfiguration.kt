package com.example.client.hystrix

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard
import org.springframework.cloud.netflix.ribbon.RibbonClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

//@EnableHystrixDashboard
@Configuration
//@RibbonClient("ribco")
@LoadBalancerClient("loabalo")
internal class HystrixConfiguration {
    @Bean
    @LoadBalanced
    fun restTemplate(): RestTemplate = RestTemplate()

}
