package de.nwoehler.parser;

import de.nwoehler.TokenIterator;
import de.nwoehler.model.statement.DeleteStatement;

// Expected format for coding task:
// DELETE FROM database.tablename WHERE {expression}
class DeleteStatementParser extends StatementParser<DeleteStatement> {

    @Override
    public DeleteStatement parse(TokenIterator tokenIterator) {
        DeleteStatement deleteStatement = new DeleteStatement();
        tokenIterator.expectToken("FROM");
        deleteStatement.setTable(tokenIterator.nextToken());
        tokenIterator.expectToken("WHERE");
        deleteStatement.setWhere(parseExpression(tokenIterator));
        tokenIterator.expectEnd();
        return deleteStatement;
    }

}
