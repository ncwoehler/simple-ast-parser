package de.nwoehler;

import com.google.common.collect.ImmutableMap;
import com.google.common.io.Resources;
import de.nwoehler.model.clause.*;
import de.nwoehler.model.function.DateFunction;
import de.nwoehler.model.function.Function;
import de.nwoehler.model.function.NumberFunction;
import de.nwoehler.model.function.StringFunction;
import de.nwoehler.model.predicate.BinaryPredicate;
import de.nwoehler.model.statement.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class SQLParserTest {

    private Statement[] expectedStatements;

    @BeforeEach
    private void setupExpected() {
        Map<String, Function> columnsToValues = new LinkedHashMap<>();
        columnsToValues.put("id", new NumberFunction(1L));
        columnsToValues.put("user_id", new NumberFunction(1L));
        columnsToValues.put("note", new StringFunction("Note 1"));
        columnsToValues.put("created", new DateFunction("NOW()"));
        expectedStatements = new Statement[]{
                new UseStatement(new UseClause("database1")),
                new SelectStatement(
                        new SelectClause(Arrays.asList("id", "name", "address")),
                        new FromClause("users"),
                        new WhereClause(new BinaryPredicate("is_customer", "IS NOT", "NULL")),
                        new OrderByClause("created")
                ),
                new InsertStatement(
                        new IntoClause("user_notes"),
                        new ValuesClause(ImmutableMap.copyOf(columnsToValues))),
                new DeleteStatement(
                        new FromClause("database2.logs"),
                        new WhereClause(new BinaryPredicate("id", "<", "1000"))
                )
        };
    }

    @Test
    void testFullInput() throws Exception {
        SQLParser parser = new SQLParser();
        var input = Resources.toString(SQLParserTest.class.getResource("operations.sql"), StandardCharsets.UTF_8);
        parser.setText(input);

        StatementList result = parser.call();

        assertThat(result.getStatements()).containsExactly(expectedStatements);
    }

    @Test
    void testMultilineInput() throws Exception {
        SQLParser parser = new SQLParser();
        var input = Resources.toString(SQLParserTest.class.getResource("multiLine.sql"), StandardCharsets.UTF_8);
        parser.setText(input);

        StatementList result = parser.call();

        assertThat(result.getStatements()).containsExactly(expectedStatements);
    }

}