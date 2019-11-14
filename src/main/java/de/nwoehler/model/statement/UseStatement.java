package de.nwoehler.model.statement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Iterator;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class UseStatement extends Statement {

    private final String database;

    // Typical format:
    // USE database
    public UseStatement(Iterator<String> tokens, int line) {
        this.database = next(tokens, line);
        expectEnd(tokens, line);
    }

    @Override
    String getStatementType() {
        return "USE";
    }
}
