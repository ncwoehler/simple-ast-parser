package de.nwoehler.parser;

import de.nwoehler.ASTParser;
import de.nwoehler.model.expression.BinaryExpression;
import de.nwoehler.model.statement.SelectStatement;
import de.nwoehler.model.statement.StatementList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SelectStatementParserTest {

    @Test
    void simpleStatement() throws Exception {
        ASTParser parser = new ASTParser();
        parser.setText("SELECT id, name FROM table1 WHERE id < 100 ORDER BY created;");

        StatementList result = parser.call();
        assertThat(result.getStatements().get(0)).isEqualTo(
                new SelectStatement(
                        Arrays.asList("id", "name"),
                        "table1",
                        new BinaryExpression("id", "<", "100"),
                        "created"
                )
        );
    }

}