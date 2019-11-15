package de.nwoehler.model.statement;

import de.nwoehler.model.expression.Expression;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DeleteStatement extends Statement {
    private String table;
    private Expression where;
}
