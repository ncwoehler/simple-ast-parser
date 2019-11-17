package de.nwoehler.parser;

import com.google.common.collect.ImmutableMap;
import de.nwoehler.TokenIterator;
import de.nwoehler.model.statement.Statement;

import java.util.Locale;
import java.util.Map;

public final class Statements {

    private static final Map<String, StatementParser> PARSERS = ImmutableMap.
            <String, StatementParser>builder()
            .put("USE", new UseStatementParser())
            .put("SELECT", new SelectStatementParser())
            .put("INSERT", new InsertStatementParser())
            .put("DELETE", new DeleteStatementParser())
            .build();

    public static Statement parse(TokenIterator tokenIterator) {
        var statementIdentifier = tokenIterator.nextToken().toUpperCase(Locale.ENGLISH);
        var statementParser = PARSERS.get(statementIdentifier);
        if (statementParser == null) {
            throw new IllegalArgumentException("Unknown statement " + statementIdentifier);
        }
        return statementParser.parse(tokenIterator);
    }
}
