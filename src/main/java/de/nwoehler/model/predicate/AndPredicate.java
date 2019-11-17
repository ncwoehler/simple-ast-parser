package de.nwoehler.model.predicate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AndPredicate extends Predicate {
    private Predicate leftPredicate;
    private Predicate rightPredicate;
}
