package de.nwoehler.model.statement;

import de.nwoehler.parser.ExpressionParser;
import de.nwoehler.model.expression.Expression;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Iterator;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class DeleteStatement extends Statement {

    private String table;
    private Expression where;

    // Typical format:
    // DELETE FROM database.tablename WHERE {expression}
    public DeleteStatement(Iterator<String> tokenIterator, int line) {

        expect(tokenIterator, line, "FROM");
        this.table = next(tokenIterator, line);
        expect(tokenIterator, line, "WHERE");
        this.where = ExpressionParser.parseExpression(tokenIterator, line);
        expectEnd(tokenIterator, line);
    }

    @Override
    String getStatementType() {
        return "DELETE";
    }
}
