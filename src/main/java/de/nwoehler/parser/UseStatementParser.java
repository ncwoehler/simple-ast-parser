package de.nwoehler.parser;

import de.nwoehler.model.statement.UseStatement;

import java.util.Iterator;

// Expected format for coding task:
// USE database;
class UseStatementParser extends StatementParser<UseStatement> {

    UseStatementParser(Iterator<String> tokens, int line) {
        super(tokens, line);
    }

    @Override
    public UseStatement parse() {
        UseStatement useStatement = new UseStatement();
        useStatement.setDatabase(nextToken());
        expectEnd();
        return useStatement;
    }

    @Override
    String getIdentifier() {
        return "USE";
    }
}
