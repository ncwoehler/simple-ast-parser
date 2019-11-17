package de.nwoehler.parser;

import de.nwoehler.ASTParser;
import de.nwoehler.model.literal.FunctionLiteral;
import de.nwoehler.model.literal.Literal;
import de.nwoehler.model.literal.NumberLiteral;
import de.nwoehler.model.literal.StringLiteral;
import de.nwoehler.model.statement.InsertStatement;
import de.nwoehler.model.statement.StatementList;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class InsertStatementParserTest {

    @Test
    void simpleStatement() throws Exception {
        ASTParser parser = new ASTParser();
        parser.setText("INSERT INTO user_notes (id, user_id, note, created) VALUES (1, 1, \"Note 1\", NOW());");

        StatementList result = parser.call();
        Map<String, Literal> columnsToValues = new LinkedHashMap<>();
        columnsToValues.put("id", new NumberLiteral(1L));
        columnsToValues.put("user_id", new NumberLiteral(1L));
        columnsToValues.put("note", new StringLiteral("Note 1"));
        columnsToValues.put("created", new FunctionLiteral("NOW()"));
        var expected = new InsertStatement("user_notes", columnsToValues);
        assertThat(result.getStatements().get(0)).isEqualTo(expected);
    }

    @Test
    @Disabled("Not supported for now")
    void withMultipleSpaces() throws Exception {
        ASTParser parser = new ASTParser();
        parser.setText("INSERT INTO user_notes (note) VALUES (\"Note   1\");");

        StatementList result = parser.call();
        Map<String, Literal> columnsToValues = new LinkedHashMap<>();
        columnsToValues.put("note", new StringLiteral("Note   1"));
        var expected = new InsertStatement("user_notes", columnsToValues);
        assertThat(result.getStatements().get(0)).isEqualTo(expected);
    }

    @Test
    void onlyFunction() throws Exception {
        ASTParser parser = new ASTParser();
        parser.setText("INSERT INTO user_notes (created) VALUES (NOW());");

        StatementList result = parser.call();
        Map<String, Literal> columnsToValues = new LinkedHashMap<>();
        columnsToValues.put("created", new FunctionLiteral("NOW()"));
        var expected = new InsertStatement("user_notes", columnsToValues);
        assertThat(result.getStatements().get(0)).isEqualTo(expected);
    }

    @Test
    void malformedWithoutFunctionBrackets() {
        ASTParser parser = new ASTParser();
        parser.setText("INSERT INTO user_notes (id, user_id, note, created) (1, 1, \"Note 1\", NOW);");

        assertThatThrownBy(parser::call).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void malformedWithoutVALUES() {
        ASTParser parser = new ASTParser();
        parser.setText("INSERT INTO user_notes (id, user_id, note, created) (1, 1, \"Note 1\", NOW());");

        assertThatThrownBy(parser::call).isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
