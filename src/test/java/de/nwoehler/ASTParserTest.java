package de.nwoehler;

import com.google.common.io.Resources;
import de.nwoehler.model.expression.AndExpression;
import de.nwoehler.model.expression.BinaryExpression;
import de.nwoehler.model.statement.*;
import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ASTParserTest {

    @Disabled("Not implemented")
    @Test
    void testFullInput() throws Exception {
        ASTParser parser = new ASTParser();
        parser.setText(Resources.toString(ASTParserTest.class.getResource("operations.sql"), StandardCharsets.UTF_8));

        StatementList result = parser.call();
        assertThat(result.getStatements()).containsExactly(
                new UseStatement("databse1"),
                new SelectStatement(

                ),
                new InsertStatement(

                ),
                new DeleteStatement(
                        "databse1.logs",
                        new BinaryExpression("id", "<", "100")
                )
        );
    }

    @Test
    void testUseStatement() throws Exception {
        ASTParser parser = new ASTParser();
        parser.setText("USE databse1;");

        StatementList result = parser.call();
        assertThat(result.getStatements().get(0)).isEqualTo(new UseStatement("databse1"));
    }

    @Test
    void testUseStatementMalformed() throws Exception {
        ASTParser parser = new ASTParser();
        parser.setText("USE databse1 as;");

        assertThatThrownBy(parser::call).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testUseStatementNoTrailingSemicolon() throws Exception {
        ASTParser parser = new ASTParser();
        parser.setText("USE databse1");

        StatementList result = parser.call();
        assertThat(result.getStatements().get(0)).isEqualTo(new UseStatement("databse1"));
    }

    @Test
    void testDeleteStatement() throws Exception {
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
    void testDeleteStatementWithAndClause() throws Exception {
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