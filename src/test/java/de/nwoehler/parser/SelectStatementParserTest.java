package de.nwoehler.parser;

import de.nwoehler.SQLParser;
import de.nwoehler.model.clause.FromClause;
import de.nwoehler.model.clause.SelectClause;
import de.nwoehler.model.statement.SelectStatement;
import de.nwoehler.model.statement.StatementList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SelectStatementParserTest {

    @Test
    void simpleStatement() {
        SQLParser parser = new SQLParser();
        parser.setText("SELECT id, name FROM table1;");

        StatementList result = parser.call();
        assertThat(result.getStatements().get(0)).isEqualTo(
                new SelectStatement(
                        new SelectClause(Arrays.asList("id", "name")),
                        new FromClause("table1"),
                       null,
                        null
                )
        );
    }

}