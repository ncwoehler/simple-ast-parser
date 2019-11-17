package de.nwoehler.model.statement;

import de.nwoehler.model.clause.UseClause;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UseStatement extends Statement {
    private UseClause use;

    @Override
    public String getType() {
        return "USE";
    }
}
