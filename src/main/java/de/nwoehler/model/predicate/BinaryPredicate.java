package de.nwoehler.model.predicate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BinaryPredicate extends Predicate {
    private final String operand1;
    private final String operator;
    private final String operand2;
}
