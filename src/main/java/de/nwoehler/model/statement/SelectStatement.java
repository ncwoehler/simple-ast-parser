package de.nwoehler.model.statement;

import de.nwoehler.model.clause.FromClause;
import de.nwoehler.model.clause.OrderByClause;
import de.nwoehler.model.clause.SelectClause;
import de.nwoehler.model.clause.WhereClause;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SelectStatement extends Statement {
    private final SelectClause selectClause;
    private final FromClause fromClause;
    private final WhereClause whereClause;
    private final OrderByClause orderByClause;
}
