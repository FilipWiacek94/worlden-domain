package com.worlden.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.worlden"])
class App

fun main(args: Array<String>) {
    runApplication<App>(*args)
}
