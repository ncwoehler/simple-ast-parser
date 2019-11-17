package de.nwoehler.model.statement;

import de.nwoehler.model.literal.Literal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class InsertStatement extends Statement {
    private String table;
    private Map<String, Literal> columnsToValues;
}
