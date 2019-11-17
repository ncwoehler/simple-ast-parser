package de.nwoehler;

import com.google.common.io.Resources;
import de.nwoehler.model.expression.BinaryExpression;
import de.nwoehler.model.literal.FunctionLiteral;
import de.nwoehler.model.literal.Literal;
import de.nwoehler.model.literal.NumberLiteral;
import de.nwoehler.model.literal.StringLiteral;
import de.nwoehler.model.statement.*;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ASTParserTest {

    @Test
    void testFullInput() throws Exception {
        ASTParser parser = new ASTParser();
        var input = Resources.toString(ASTParserTest.class.getResource("operations.sql"), StandardCharsets.UTF_8);
        parser.setText(input);

        StatementList result = parser.call();

        Map<String, Literal> columnsToValues = new LinkedHashMap<>();
        columnsToValues.put("id", new NumberLiteral(1L));
        columnsToValues.put("user_id", new NumberLiteral(1L));
        columnsToValues.put("note", new StringLiteral("Note 1"));
        columnsToValues.put("created", new FunctionLiteral("NOW()"));

        assertThat(result.getStatements()).containsExactly(
                new UseStatement("database1"),
                new SelectStatement(
                        Arrays.asList("id", "name", "address"),
                        "users",
                        new BinaryExpression("is_customer", "IS NOT", "NULL"),
                        "created"
                ),
                new InsertStatement("user_notes", columnsToValues),
                new DeleteStatement(
                        "database2.logs",
                        new BinaryExpression("id", "<", "1000")
                )
        );
    }

}