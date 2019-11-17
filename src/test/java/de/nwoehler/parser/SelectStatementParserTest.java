package de.nwoehler.parser;

import de.nwoehler.SQLParser;
import de.nwoehler.model.clause.FromClause;
import de.nwoehler.model.clause.OrderByClause;
import de.nwoehler.model.clause.SelectClause;
import de.nwoehler.model.clause.WhereClause;
import de.nwoehler.model.predicate.BinaryPredicate;
import de.nwoehler.model.statement.SelectStatement;
import de.nwoehler.model.statement.StatementList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

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

    @Test
    void simpleStatementWithWhere() {
        SQLParser parser = new SQLParser();
        parser.setText("SELECT id, name FROM table1 WHERE id < 100;");

        StatementList result = parser.call();
        assertThat(result.getStatements().get(0)).isEqualTo(
                new SelectStatement(
                        new SelectClause(Arrays.asList("id", "name")),
                        new FromClause("table1"),
                        new WhereClause(new BinaryPredicate("id", "<", "100")),
                        null
                )
        );
    }

    @Test
    void simpleStatementWithWhereAndOrderBy() {
        SQLParser parser = new SQLParser();
        parser.setText("SELECT id, name FROM table1 WHERE id < 100 ORDER BY created;");

        StatementList result = parser.call();
        assertThat(result.getStatements().get(0)).isEqualTo(
                new SelectStatement(
                        new SelectClause(Arrays.asList("id", "name")),
                        new FromClause("table1"),
                        new WhereClause(new BinaryPredicate("id", "<", "100")),
                        new OrderByClause("created")
                )
        );
    }


    @Test
    void simpleStatementWithOrderBy() {
        SQLParser parser = new SQLParser();
        parser.setText("SELECT id, name FROM table1 ORDER BY created;");

        StatementList result = parser.call();
        assertThat(result.getStatements().get(0)).isEqualTo(
                new SelectStatement(
                        new SelectClause(Arrays.asList("id", "name")),
                        new FromClause("table1"),
                        null,
                        new OrderByClause("created")
                )
        );
    }

    @Test
    void statementWithMissingColumn() {
        SQLParser parser = new SQLParser();
        parser.setText("SELECT id, , name FROM table1 ORDER BY created;");

        assertThatThrownBy(parser::call).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void statementWithMissingOrderBy() {
        SQLParser parser = new SQLParser();
        parser.setText("SELECT id, name FROM users  ORDER BY ;");

        assertThatThrownBy(parser::call).isExactlyInstanceOf(IllegalArgumentException.class);
    }

}