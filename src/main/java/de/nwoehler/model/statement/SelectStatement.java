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
    private final SelectClause select;
    private final FromClause from;
    private final WhereClause where;
    private final OrderByClause orderBy;

    @Override
    public String getType() {
        return "SELECT";
    }
}
