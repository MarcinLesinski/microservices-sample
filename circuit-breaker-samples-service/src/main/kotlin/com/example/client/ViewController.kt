package com.example.client

import com.example.client.hystrix.HystrixSampleClient
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class ViewController(
    private val hystrixClient: HystrixSampleClient,
    private val feignWithFallbackClient: FeignSampleClientWithFallback,
    @Value("\${spring.application.name}") private val appName: String
    ) {
//    @HystrixCommand(
//        fallbackMethod = "fallback"
//    )
    @GetMapping("/samples")
    public fun samples(model: Model): String {

        model.addAttribute("app_name", appName)
        model.addAttribute("numbers_provider_port", hystrixClient.getPort())
//        model.addAttribute("hystrix_web_offline_fallback", hystrixClient.getWebData("***"))
//        model.addAttribute("hystrix_exception_fallback", hystrixClient.getData())
//        model.addAttribute("feign_web_offline_fallback", feignWithFallbackClient.getValidData())
//        model.addAttribute("hystrix_timeout", hystrixClient.timeoutData())
//        model.addAttribute("hystrix_errors", hystrixClient.everyFifth())
//        model.addAttribute("feign_timeout", feignWithFallbackClient.getDelayedData(2000))


        return "sample-delay"
    }

    private fun fallback(model: Model): String{
        return "sample-delay"
    }
}
