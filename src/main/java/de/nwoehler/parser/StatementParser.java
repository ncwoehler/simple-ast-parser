package de.nwoehler.parser;

import de.nwoehler.TokenIterator;
import de.nwoehler.model.statement.Statement;
import lombok.AllArgsConstructor;

@AllArgsConstructor
abstract class StatementParser<T extends Statement> {

    /**
     * Parses the statement based on the provided token iterator.
     *
     * @return the statement
     */
    public abstract T parse(TokenIterator iterator);

}
