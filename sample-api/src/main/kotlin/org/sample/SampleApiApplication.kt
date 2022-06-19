package org.sample

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class SampleApiApplication {
}

fun main(args: Array<String>) {
    SpringApplication.run(SampleApiApplication::class.java, *args)
}