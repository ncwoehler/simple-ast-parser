package de.nwoehler.model.predicate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BinaryPredicate extends Predicate {
    private String operand1;
    private String operator;
    private String operand2;
}
