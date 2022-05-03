package com.example.client

import com.example.server.FullOfFlawsController
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component

@FeignClient(
//    primary = true,
    name = "http://localhost:9001/",
    url = "http://localhost:9001/",
    fallback = FeignSampleClientWithFallback.FeignClientWithFallbackFallback::class
)
interface FeignSampleClientWithFallback: FullOfFlawsController {

    @Component
    class FeignClientWithFallbackFallback: FeignSampleClientWithFallback {
        override fun getValidData(): String {
            return "fallback"
        }

        override fun getDelayedData(timeInMs: Long): String {
            return "fallback"
        }

        override fun getValidEveryFifth(): String {
            return "fallback"
        }
    }
}




//@FeignClient(
//    name = "server",
//    url = "http://localhost:9001/",
//    fallback = SampleClient.SampleClientFallback::class
//)
//interface SampleClient: DelayController {
//
//
//    @Component
//    class SampleClientFallback(): DelayController{
//        override fun getDelay(@PathVariable("timeInMs") timeInMs: Long): String {
//            return "fallback"
//        }
//    }
//}

