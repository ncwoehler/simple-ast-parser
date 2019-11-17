package de.nwoehler.controller

import de.nwoehler.controller.dto.ASTResponse
import de.nwoehler.controller.dto.ParseRequest
import de.nwoehler.controller.dto.ParsingError
import de.nwoehler.service.ASTParserService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.validation.Valid


@Controller
class MainController(private val astParserService: ASTParserService) {

    @RequestMapping(value = ["/", "/index"], method = [RequestMethod.GET])
    fun index(model: Model) = "index"

    @PostMapping("/api/parse")
    fun parse(
            @Valid @RequestBody request: ParseRequest): ResponseEntity<*> {
        val statements = try {
             astParserService.parseAST(request.sql)
        } catch(e: IllegalArgumentException) {
            return ResponseEntity.badRequest().body(ParsingError(e.localizedMessage))
        }
        return ResponseEntity.ok<Any>(ASTResponse(statements))

    }
}