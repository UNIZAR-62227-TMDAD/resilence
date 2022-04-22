package eureka.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class MyEurekaServer

fun main(args: Array<String>) {
    runApplication<MyEurekaServer>(*args)
}
