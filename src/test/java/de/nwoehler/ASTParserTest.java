package de.nwoehler;

import com.google.common.collect.ImmutableMap;
import com.google.common.io.Resources;
import de.nwoehler.model.clause.*;
import de.nwoehler.model.predicate.BinaryPredicate;
import de.nwoehler.model.function.DateFunction;
import de.nwoehler.model.function.Function;
import de.nwoehler.model.function.NumberFunction;
import de.nwoehler.model.function.StringFunction;
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

        Map<String, Function> columnsToValues = new LinkedHashMap<>();
        columnsToValues.put("id", new NumberFunction(1L));
        columnsToValues.put("user_id", new NumberFunction(1L));
        columnsToValues.put("note", new StringFunction("Note 1"));
        columnsToValues.put("created", new DateFunction("NOW()"));

        assertThat(result.getStatements()).containsExactly(
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
        );
    }

}