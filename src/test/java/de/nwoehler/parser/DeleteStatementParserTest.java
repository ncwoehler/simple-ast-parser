package de.nwoehler.parser;

import de.nwoehler.ASTParser;
import de.nwoehler.model.expression.AndExpression;
import de.nwoehler.model.expression.BinaryExpression;
import de.nwoehler.model.statement.DeleteStatement;
import de.nwoehler.model.statement.StatementList;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DeleteStatementParserTest {

    @Test
    void simpleStatement() throws Exception {
        ASTParser parser = new ASTParser();
        parser.setText("DELETE FROM databse1.logs WHERE id < 100;");

        StatementList result = parser.call();
        assertThat(result.getStatements().get(0)).isEqualTo(
                new DeleteStatement(
                        "databse1.logs",
                        new BinaryExpression("id", "<", "100")
                )
        );
    }

    @Disabled("Not implemented")
    @Test
    void withAndClause() throws Exception {
        ASTParser parser = new ASTParser();
        parser.setText("DELETE FROM databse1.logs WHERE id < 100 AND count > 1;");

        StatementList result = parser.call();
        assertThat(result.getStatements().get(0)).isEqualTo(
                new DeleteStatement(
                        "databse1.logs",
                        new AndExpression(
                                new BinaryExpression("id", "100", "<"),
                                new BinaryExpression("count", "1", ">")
                        )
                )
        );
    }
}
