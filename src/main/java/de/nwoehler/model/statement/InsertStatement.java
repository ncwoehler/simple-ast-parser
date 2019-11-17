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
    private final IntoClause intoClause;
    private final ValuesClause valuesClause;
}
