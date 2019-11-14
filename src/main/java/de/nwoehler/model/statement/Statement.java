package de.nwoehler.model.statement;

import lombok.Data;

import java.util.Iterator;

@Data
public abstract class Statement {

    abstract String getStatementType();

    String next(Iterator<String> iterator, int line) {
        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("Malformed " + getStatementType() + " statement at line " + line);
        }
        return iterator.next();
    }

    String expect(Iterator<String> iterator, int line, String expected) {
        var next = next(iterator, line);
        if (!expected.equalsIgnoreCase(next)) {
            throw new IllegalArgumentException("Malformed " + getStatementType() + " statement at line " + line);
        }
        return next;
    }

    void expectEnd(Iterator<String> iterator, int line) {
        if (iterator.hasNext()) {
            throw new IllegalArgumentException("Malformed " + getStatementType() + " statement at line " + line);
        }
    }
}
