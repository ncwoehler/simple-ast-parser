package de.nwoehler

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimpleAstParserApplication

fun main(args: Array<String>) {
    runApplication<SimpleAstParserApplication>(*args)
}
