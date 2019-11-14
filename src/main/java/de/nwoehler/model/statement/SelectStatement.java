package de.nwoehler.model.statement;

import de.nwoehler.parser.ExpressionParser;
import de.nwoehler.model.expression.Expression;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class SelectStatement extends Statement {

    private List<String> columns = new ArrayList<>();
    private String table;
    private Expression where;
    private String orderedBy;

    public SelectStatement(Iterator<String> tokens, int line) {
       String nextToken = next(tokens, line);
        while(!nextToken.equalsIgnoreCase("FROM")) {
            columns.add(nextToken.replace(",", ""));
            nextToken = next(tokens, line);
        }

        this.table = next(tokens, line);
        expect(tokens, line, "WHERE");
        this.where = ExpressionParser.parseExpression(tokens, line);
        expect(tokens, line, "ORDER");
        expect(tokens, line, "BY");
        this.orderedBy = next(tokens, line);
        expectEnd(tokens, line);
    }

    @Override
    String getStatementType() {
        return "SELECT";
    }
}
