package de.nwoehler.model.statement;

import de.nwoehler.model.clause.IntoClause;
import de.nwoehler.model.clause.ValuesClause;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class InsertStatement extends Statement {
    private final IntoClause into;
    private final ValuesClause values;

    @Override
    public String getType() {
        return "INSERT";
    }
}
