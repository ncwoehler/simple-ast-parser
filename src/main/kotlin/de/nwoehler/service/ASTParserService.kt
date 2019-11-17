package de.nwoehler.service

import de.nwoehler.SQLParser
import de.nwoehler.model.statement.StatementList
import org.springframework.stereotype.Service

@Service
class ASTParserService {

    fun parseAST(input: String): StatementList {
        return SQLParser(input).call()
    }
}