package de.nwoehler.parser;

import de.nwoehler.model.statement.SelectStatement;

import java.util.Iterator;

// Expected format for coding task:
// SELECT column1, column2 FROM table WHERE {expression} ORDER BY column;
class SelectStatementParser extends StatementParser<SelectStatement> {

    SelectStatementParser(Iterator<String> tokens, int line) {
        super(tokens, line);
    }

    @Override
    public SelectStatement parse() {
        SelectStatement selectStatement = new SelectStatement();
        String nextToken = nextToken();
        while(!nextToken.equalsIgnoreCase("FROM")) {
            var columnToken = nextToken.replace(",", "");
            selectStatement.getColumns().add(columnToken);
            nextToken = nextToken();
        }
        selectStatement.setTable(nextToken());
        expectToken("WHERE");
        selectStatement.setWhere(parseExpression());
        expectToken("ORDER", "BY");
        selectStatement.setOrderedBy(nextToken());
        expectEnd();
        return selectStatement;
    }

    @Override
    String getIdentifier() {
        return "SELECT";
    }
}
