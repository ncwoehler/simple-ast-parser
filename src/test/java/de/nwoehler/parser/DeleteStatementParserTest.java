package de.nwoehler.parser;

import de.nwoehler.SQLParser;
import de.nwoehler.model.clause.FromClause;
import de.nwoehler.model.clause.WhereClause;
import de.nwoehler.model.predicate.AndPredicate;
import de.nwoehler.model.predicate.BinaryPredicate;
import de.nwoehler.model.statement.DeleteStatement;
import de.nwoehler.model.statement.StatementList;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DeleteStatementParserTest {

    @Test
    void simpleStatement() {
        SQLParser parser = new SQLParser("DELETE FROM databse1.logs WHERE id < 100;");

        StatementList result = parser.call();
        assertThat(result.getStatements().get(0)).isEqualTo(
                new DeleteStatement(
                        new FromClause("databse1.logs"),
                        new WhereClause(new BinaryPredicate("id", "<", "100"))
                )
        );
    }

    @Disabled("Not implemented")
    @Test
    void withAndClause() {
        SQLParser parser = new SQLParser("DELETE FROM databse1.logs WHERE id < 100 AND count > 1;");

        StatementList result = parser.call();
        assertThat(result.getStatements().get(0)).isEqualTo(
                new DeleteStatement(
                        new FromClause("databse1.logs"),
                        new WhereClause(
                                new AndPredicate(
                                        new BinaryPredicate("id", "100", "<"),
                                        new BinaryPredicate("count", "1", ">")
                                )
                        )
                )
        );
    }
}
