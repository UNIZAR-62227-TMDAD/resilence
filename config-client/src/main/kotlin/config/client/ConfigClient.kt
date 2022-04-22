package config.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@SpringBootApplication
class ConfigClient

fun main(args: Array<String>) {
    runApplication<ConfigClient>(*args)
}

@RestController
class Controller {
    @Value("\${foo.bar}")
    private lateinit var meaningOfLife: String

    @GetMapping(
        value = ["/life"],
        produces = [MediaType.TEXT_PLAIN_VALUE]
    )
    fun life() = "The meaning of life is $meaningOfLife"
}