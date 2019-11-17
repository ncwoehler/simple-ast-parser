package de.nwoehler.parser;

import de.nwoehler.TokenIterator;
import de.nwoehler.model.statement.SelectStatement;

// Expected format for coding task:
// SELECT column1, column2 FROM table WHERE {expression} ORDER BY column;
class SelectStatementParser extends StatementParser<SelectStatement> {

    @Override
    public SelectStatement parse(TokenIterator tokenIterator) {
        SelectStatement selectStatement = new SelectStatement();
        String nextToken = tokenIterator.nextToken();
        while(!nextToken.equalsIgnoreCase("FROM")) {
            var columnToken = nextToken.replace(",", "");
            selectStatement.getColumns().add(columnToken);
            nextToken = tokenIterator.nextToken();
        }
        selectStatement.setTable(tokenIterator.nextToken());
        tokenIterator.expectToken("WHERE");
        selectStatement.setWhere(parseExpression(tokenIterator));
        tokenIterator.expectToken("ORDER", "BY");
        selectStatement.setOrderedBy(tokenIterator.nextToken());
        tokenIterator.expectEnd();
        return selectStatement;
    }

}
