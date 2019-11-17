package de.nwoehler.model.clause;

import com.google.common.collect.ImmutableMap;
import de.nwoehler.model.function.Function;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ValuesClause {
    private final ImmutableMap<String, Function> columnsToValues;
}
