package de.nwoehler;

import com.google.common.base.Splitter;
import de.nwoehler.model.statement.StatementList;
import de.nwoehler.parser.Statements;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.Callable;
import java.util.regex.Pattern;

@Data
@NoArgsConstructor
public class SQLParser implements Callable<StatementList> {

    private String text;

    public SQLParser(String text) {
        this.text = text;
    }

    @Override
    public StatementList call() {
        if (text == null) {
            throw new IllegalArgumentException("Neither SQL file nor text input are defined");
        }
        String sqlInput = text;
        if (sqlInput.strip().isBlank()) {
            throw new IllegalArgumentException("Failed to parse AST. Provided input is empty.");
        }

        return parse(sqlInput);
    }

    private StatementList parse(String sqlInput) {
        StatementList statementList = new StatementList();

        // Let's keep it simple, assume each SQL statement ends with a ;
        var statementLines = Splitter.on(";").trimResults().omitEmptyStrings().split(sqlInput);
        int line = 1;
        for (String statementLine : statementLines) {
            // Split each SQL statement into its tokens
            var tokens = Splitter.on(Pattern.compile("\\s+")).split(statementLine);
            // Skip empty statements
            var iterator = tokens.iterator();
            if (!iterator.hasNext()) {
                continue;
            }
            var tokenIterator = new TokenIterator(iterator, line);
            statementList.getStatements().add(Statements.parse(tokenIterator));
            line++;
        }
        return statementList;
    }

}
