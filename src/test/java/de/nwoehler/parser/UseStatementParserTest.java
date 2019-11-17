package de.nwoehler.parser;

import de.nwoehler.ASTParser;
import de.nwoehler.model.clause.UseClause;
import de.nwoehler.model.statement.StatementList;
import de.nwoehler.model.statement.UseStatement;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class UseStatementParserTest {

    @Test
    void simpleStatement() throws Exception {
        ASTParser parser = new ASTParser();
        parser.setText("USE databse1;");

        StatementList result = parser.call();
        assertThat(result.getStatements().get(0)).isEqualTo(new UseStatement(new UseClause("databse1")));
    }

    @Test
    void malformed() {
        ASTParser parser = new ASTParser();
        parser.setText("USE databse1 as;");

        assertThatThrownBy(parser::call).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void noTrailingSemicolon() throws Exception {
        ASTParser parser = new ASTParser();
        parser.setText("USE databse1");

        StatementList result = parser.call();
        assertThat(result.getStatements().get(0)).isEqualTo(new UseStatement(new UseClause("databse1")));
    }


}
