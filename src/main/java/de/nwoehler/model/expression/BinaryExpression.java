package de.nwoehler.model.expression;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class BinaryExpression extends Expression {
    private String operand1;
    private String operator;
    private String operand2;
}
