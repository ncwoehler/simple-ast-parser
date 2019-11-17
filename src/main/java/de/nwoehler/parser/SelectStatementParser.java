package de.nwoehler.parser;

import de.nwoehler.TokenIterator;
import de.nwoehler.model.clause.FromClause;
import de.nwoehler.model.clause.OrderByClause;
import de.nwoehler.model.clause.SelectClause;
import de.nwoehler.model.clause.WhereClause;
import de.nwoehler.model.statement.SelectStatement;

import java.util.ArrayList;
import java.util.List;

// Expected format for coding task:
// SELECT column1, column2 FROM table WHERE {expression} ORDER BY column;
class SelectStatementParser extends StatementParser<SelectStatement> {

    @Override
    public SelectStatement parse(TokenIterator tokenIterator) {

        List<String> columns = new ArrayList<>();
        String nextToken = tokenIterator.nextToken();
        while (!nextToken.equalsIgnoreCase("FROM")) {
            // assume no columns contain a comma by default
            var columnToken = nextToken.replace(",", "");
            columns.add(columnToken);
            nextToken = tokenIterator.nextToken();
        }
        var selectClause = new SelectClause(columns);
        var fromClause = new FromClause(tokenIterator.nextToken());
        tokenIterator.expectToken("WHERE");
        var whereClause = new WhereClause(PredicateParser.parse(tokenIterator));
        tokenIterator.expectToken("ORDER", "BY");
        var orderByClause = new OrderByClause(tokenIterator.nextToken());
        tokenIterator.expectEnd();
        return new SelectStatement(selectClause, fromClause, whereClause, orderByClause);
    }

}
