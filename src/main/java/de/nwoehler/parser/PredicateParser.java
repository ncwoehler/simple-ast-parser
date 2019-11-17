package de.nwoehler.parser;

import de.nwoehler.TokenIterator;
import de.nwoehler.model.predicate.BinaryPredicate;
import de.nwoehler.model.predicate.Predicate;

class PredicateParser {

    static Predicate parse(TokenIterator tokenIterator) {
        var firstOperand = tokenIterator.nextToken();
        var operator = tokenIterator.nextToken();
        var secondOperand = tokenIterator.nextToken();
        if (operator.equalsIgnoreCase("IS")) {
            operator += " " + secondOperand;
            secondOperand = tokenIterator.nextToken();
        }
        return new BinaryPredicate(firstOperand, operator, secondOperand);
    }
}
