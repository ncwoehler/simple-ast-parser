package de.nwoehler.parser;

import de.nwoehler.model.expression.Expression;
import de.nwoehler.model.statement.Statement;
import lombok.AllArgsConstructor;

import java.util.Iterator;

@AllArgsConstructor
abstract class StatementParser<T extends Statement> {

    private Iterator<String> iterator;
    private int line;

    /**
     * Parses the statement based on the provided token iterator.
     *
     * @return the statement
     */
    public abstract T parse();

    /**
     * Returns the statement identifier
     *
     * @return the statement identifier
     */
    abstract String getIdentifier();

    /**
     * Reads the next available token. Fails if no further token is available.
     *
     * @return the next token
     */
    String nextToken() {
        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("Malformed " + getIdentifier() + " statement at line " + line);
        }
        return iterator.next();
    }

    /**
     * For every provided token it reads the next available tolen and checks whether they match the expected token.
     * Throws an error in case it does not match.
     *
     * @param expected the token(s) that are expected next
     */
    void expectToken(String... expected) {
       for(String expectedToken : expected) {
           var next = nextToken();
           if (!expectedToken.equalsIgnoreCase(next)) {
               throw new IllegalArgumentException("Malformed " + getIdentifier() + " statement at line " + line);
           }
       }
    }

    Expression parseExpression() {
        return ExpressionParser.parseExpression(iterator, line);
    }

    /**
     * Checks that no further token is available. Fails in case another token is available.
     */
    void expectEnd() {
        if (iterator.hasNext()) {
            throw new IllegalArgumentException("Malformed " + getIdentifier() + " statement at line " + line);
        }
    }
}
