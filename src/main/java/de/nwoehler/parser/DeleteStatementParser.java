package de.nwoehler.parser;

import de.nwoehler.model.statement.DeleteStatement;

import java.util.Iterator;

// Expected format for coding task:
// DELETE FROM database.tablename WHERE {expression}
class DeleteStatementParser extends StatementParser<DeleteStatement> {

    DeleteStatementParser(Iterator<String> tokens, int line) {
        super(tokens, line);
    }

    @Override
    public DeleteStatement parse() {
        DeleteStatement deleteStatement = new DeleteStatement();
        expectToken("FROM");
        deleteStatement.setTable(nextToken());
        expectToken("WHERE");
        deleteStatement.setWhere(parseExpression());
        expectEnd();
        return deleteStatement;
    }


    @Override
    String getIdentifier() {
        return "DELETE";
    }
}
