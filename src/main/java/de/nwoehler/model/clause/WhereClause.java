package de.nwoehler.model.clause;

import de.nwoehler.model.predicate.Predicate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WhereClause {
    private Predicate predicate;
}
