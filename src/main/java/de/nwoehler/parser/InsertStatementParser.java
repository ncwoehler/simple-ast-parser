package de.nwoehler.parser;

import com.google.common.collect.ImmutableMap;
import de.nwoehler.TokenIterator;
import de.nwoehler.model.clause.IntoClause;
import de.nwoehler.model.clause.ValuesClause;
import de.nwoehler.model.function.DateFunction;
import de.nwoehler.model.function.Function;
import de.nwoehler.model.function.NumberFunction;
import de.nwoehler.model.function.StringFunction;
import de.nwoehler.model.statement.InsertStatement;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Expected format for coding task:
// INSERT INTO table1 (id, user_id, note, created) VALUES (1, 1, "Note 1", NOW());
class InsertStatementParser extends StatementParser<InsertStatement> {

    @Override
    public InsertStatement parse(TokenIterator tokenIterator) {
        tokenIterator.expectToken("INTO");
        var intoClause = new IntoClause(tokenIterator.nextToken());
        var valuesClause = parseValuesClause(tokenIterator);
        tokenIterator.expectEnd();
        return new InsertStatement(intoClause, valuesClause);
    }

    private List<String> parseColumns(TokenIterator tokenIterator) {
        List<String> columns = new ArrayList<>();
        var columnToken = tokenIterator.nextToken();
        if (!columnToken.startsWith("(")) {
            throw new IllegalArgumentException(tokenIterator.getErrorMessage());
        }
        while (!columnToken.equalsIgnoreCase("VALUES")) {
            var sanitizedColumn = sanitizeColumn(columnToken);
            columnToken = tokenIterator.nextToken();
            if (sanitizedColumn.isBlank()) {
                continue;
            }
            columns.add(sanitizedColumn);
        }
        if (columns.isEmpty()) {
            throw new IllegalArgumentException(tokenIterator.getErrorMessage("No columns defined"));
        }
        return columns;
    }

    private String sanitizeColumn(String input) {
        return input
                .replace("(", "")
                .replace(")", "")
                .replace(",", "");
    }

    private ValuesClause parseValuesClause(TokenIterator tokenIterator) {
        var columns = parseColumns(tokenIterator);
        boolean firstValue = true;
        Map<String, Function> columnsToValues = new LinkedHashMap<>();
        for (int index = 0; index < columns.size(); index++) {
            String column = columns.get(index);
            String valueToken = tokenIterator.nextToken();
            if (firstValue) {
                firstValue = false;
                if (!valueToken.startsWith("(")) {
                    throw new IllegalArgumentException(tokenIterator.getErrorMessage());
                }
                // strip ()) from first value
                valueToken = valueToken.substring(1);
            }
            if (index == columns.size() - 1) {
                if (!valueToken.endsWith(")")) {
                    throw new IllegalArgumentException(tokenIterator.getErrorMessage());
                }
                // strip ) from last value
                valueToken = valueToken.substring(0, valueToken.length() - 1);
            }
            valueToken = sanitizeValue(valueToken);

            if (valueToken.startsWith("\"")) {
                columnsToValues.put(column, new StringFunction(readStringValue(valueToken.substring(1), tokenIterator)));
            } else if (valueToken.endsWith("()")) {
                columnsToValues.put(column, new DateFunction(valueToken));
            } else {
                try {
                    columnsToValues.put(column, new NumberFunction(NumberFormat.getInstance().parse(valueToken)));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(tokenIterator.getErrorMessage());
                }
            }
        }
        return new ValuesClause(ImmutableMap.copyOf(columnsToValues));
    }

    private String readStringValue(String firstToken, TokenIterator tokenIterator) {
        StringBuilder builder = new StringBuilder();
        builder.append(firstToken);
        boolean stringEnd = false;
        while (!stringEnd) {
            String nextToken = sanitizeValue(tokenIterator.nextToken());
            stringEnd = nextToken.endsWith("\"");
            builder.append(" ");
            builder.append(stringEnd ? nextToken.substring(0, nextToken.length() - 1) : nextToken);
        }
        return builder.toString();
    }

    private String sanitizeValue(String input) {
        return input.replace(",", "");
    }

}
