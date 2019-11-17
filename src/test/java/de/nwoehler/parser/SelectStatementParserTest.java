package de.nwoehler.parser;

import de.nwoehler.ASTParser;
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

class SelectStatementParserTest {

    @Test
    void simpleStatement() throws Exception {
        ASTParser parser = new ASTParser();
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

}