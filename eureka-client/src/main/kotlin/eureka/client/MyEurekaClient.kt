package eureka.client

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@EnableEurekaClient
class MyEurekaClient {
    @LoadBalanced
    @Bean
    fun restTemplate(): RestTemplate = RestTemplate()

}

fun main(args: Array<String>) {
    runApplication<MyEurekaClient>(*args)
}

@Component
class MyCommand : CommandLineRunner {

    @Autowired
    private lateinit var restTemplate: RestTemplate

    @Autowired
    private lateinit var circuitBreakerFactory: CircuitBreakerFactory<*, *>

    override fun run(vararg args: String?) {
        val result = safe {
                restTemplate.getForObject("http://bar/life", String::class.java)
            }.onFailure {
                println("Ha fallado por ${it.message}")
        }.getOrDefault("Ha fallado, pero continuo" )
        println(result)
    }

    fun <T> safe(block: ()->T): Result<T> = runCatching {
        circuitBreakerFactory
            .create("client")
            .run(block) { throw it }
    }
}