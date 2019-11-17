package de.nwoehler.model.predicate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AndPredicate extends Predicate {
    private final Predicate leftPredicate;
    private final Predicate rightPredicate;
}
