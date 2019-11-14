package de.nwoehler.parser;

import com.google.common.base.Splitter;
import de.nwoehler.model.statement.*;

import java.util.Locale;
import java.util.regex.Pattern;

public class StatementParser {

   public static StatementList parseStatementList(String input) {
        StatementList statementList = new StatementList();

        // Let's keep it simple, assume each SQL statement ends with a ;
        var statementLines = Splitter.on(";").trimResults().omitEmptyStrings().split(input);
        int line = 1;
        for (String statementLine : statementLines) {
            // Split each SQL statement into its tokens
            var tokens = Splitter.on(Pattern.compile("\\s+")).split(statementLine);
            var tokenIterator = tokens.iterator();
            if(!tokenIterator.hasNext()) {
                // Skip empty statements
                continue;
            }
            var statementIdentifier = tokenIterator.next().toUpperCase(Locale.ENGLISH);
            Statement nextStatement;
            switch (statementIdentifier) {
                case "USE":
                    nextStatement = new UseStatement(tokenIterator, line);
                    break;
                case "SELECT":
                    nextStatement = new SelectStatement(tokenIterator, line);
                    break;
                case "INSERT":
                    nextStatement = new InsertStatement(tokenIterator, line);
                    break;
                case "DELETE":
                    nextStatement = new DeleteStatement(tokenIterator, line);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown statement " + statementIdentifier);

            }
            statementList.getStatements().add(nextStatement);
            line++;
        }
        return statementList;
    }

}
