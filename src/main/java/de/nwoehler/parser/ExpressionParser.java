package de.nwoehler.parser;

import de.nwoehler.TokenIterator;
import de.nwoehler.model.expression.BinaryExpression;
import de.nwoehler.model.expression.Expression;

class ExpressionParser {

    static Expression parseExpression(TokenIterator tokenIterator) {
        var firstOperand = tokenIterator.nextToken();
        var operator = tokenIterator.nextToken();
        var secondOperand = tokenIterator.nextToken();
        if (operator.equalsIgnoreCase("IS")) {
            operator += " " + secondOperand;
            secondOperand = tokenIterator.nextToken();
        }
        return new BinaryExpression(firstOperand, operator, secondOperand);
    }
}
