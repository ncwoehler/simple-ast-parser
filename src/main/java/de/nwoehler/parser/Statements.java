package de.nwoehler.parser;

import de.nwoehler.model.statement.Statement;

import java.util.Iterator;
import java.util.Locale;

public final class Statements {

    public static Statement parse(Iterator<String> tokenIterator, int line) {
        // Skip empty statements
        if(!tokenIterator.hasNext()) {
            return null;
        }
        var statementIdentifier = tokenIterator.next().toUpperCase(Locale.ENGLISH);
        Statement nextStatement;
        switch (statementIdentifier) {
            case "USE":
                nextStatement = new UseStatementParser(tokenIterator, line).parse();
                break;
            case "SELECT":
                nextStatement = new SelectStatementParser(tokenIterator, line).parse();
                break;
            case "INSERT":
                nextStatement = new InsertStatementParser(tokenIterator, line).parse();
                break;
            case "DELETE":
                nextStatement = new DeleteStatementParser(tokenIterator, line).parse();
                break;
            default:
                throw new IllegalArgumentException("Unknown statement " + statementIdentifier);

        }
        return nextStatement;
    }
}
