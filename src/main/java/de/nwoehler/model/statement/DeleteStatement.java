package de.nwoehler.model.statement;

import de.nwoehler.model.clause.FromClause;
import de.nwoehler.model.clause.WhereClause;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DeleteStatement extends Statement {
    private final FromClause from;
    private final WhereClause where;

    @Override
    public String getType() {
        return "DELETE";
    }
}
