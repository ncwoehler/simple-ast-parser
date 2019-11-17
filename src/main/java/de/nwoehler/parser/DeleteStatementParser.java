package de.nwoehler.parser;

import de.nwoehler.TokenIterator;
import de.nwoehler.model.clause.FromClause;
import de.nwoehler.model.clause.WhereClause;
import de.nwoehler.model.statement.DeleteStatement;

// Expected format for coding task:
// DELETE FROM database.tablename WHERE {predicate}
class DeleteStatementParser extends StatementParser<DeleteStatement> {

    @Override
    public DeleteStatement parse(TokenIterator tokenIterator) {
        tokenIterator.expectToken("FROM");
        var fromClause= new FromClause(tokenIterator.nextToken());
        tokenIterator.expectToken("WHERE");
        var whereClause = new WhereClause(PredicateParser.parse(tokenIterator));
        tokenIterator.expectEnd();
        return new DeleteStatement(fromClause, whereClause);
    }

}
