package de.nwoehler.parser;

import de.nwoehler.model.statement.InsertStatement;
import de.nwoehler.model.statement.SelectStatement;

import java.util.Iterator;

// Expected format for coding task:
// SELECT column1, column2 FROM table WHERE {expression} ORDER BY column;
class InsertStatementParser extends StatementParser<InsertStatement> {

    InsertStatementParser(Iterator<String> tokens, int line) {
        super(tokens, line);
    }

    @Override
    public InsertStatement parse() {
        InsertStatement insertStatement = new InsertStatement();
        return insertStatement;
    }

    @Override
    String getIdentifier() {
        return "INSERT";
    }
}
