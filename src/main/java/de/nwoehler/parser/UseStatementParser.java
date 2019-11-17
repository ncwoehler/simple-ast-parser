package de.nwoehler.parser;

import de.nwoehler.TokenIterator;
import de.nwoehler.model.clause.UseClause;
import de.nwoehler.model.statement.UseStatement;

// Expected format for coding task:
// USE database;
class UseStatementParser extends StatementParser<UseStatement> {

    @Override
    public UseStatement parse(TokenIterator tokenIterator) {
        UseStatement useStatement = new UseStatement();
        useStatement.setUseClause(new UseClause(tokenIterator.nextToken()));
        tokenIterator.expectEnd();
        return useStatement;
    }

}
