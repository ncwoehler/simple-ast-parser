package de.nwoehler.model.statement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Iterator;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class SelectStatement extends Statement {

    public SelectStatement(Iterator<String> tokens, int line) {
        // TODO
    }

    @Override
    String getStatementType() {
        return "SELECT";
    }
}
