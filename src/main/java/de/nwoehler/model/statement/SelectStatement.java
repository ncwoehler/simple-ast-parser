package de.nwoehler.model.statement;

import de.nwoehler.model.expression.Expression;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SelectStatement extends Statement {
    private List<String> columns = new ArrayList<>();
    private String table;
    private Expression where;
    private String orderedBy;
}
