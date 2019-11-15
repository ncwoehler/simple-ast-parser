package de.nwoehler.parser;

import de.nwoehler.model.expression.BinaryExpression;
import de.nwoehler.model.expression.Expression;

import java.util.Iterator;

class ExpressionParser {

    private static final String[] OPERATORS = {
            "<",
            ">"
    };

    static Expression parseExpression(Iterator<String> tokenIterator, int line) {
        // https://de.wikipedia.org/wiki/Shunting-yard-Algorithmus ?

        // TODO better parsing
        return new BinaryExpression(tokenIterator.next(), tokenIterator.next(), tokenIterator.next());
    }
}
