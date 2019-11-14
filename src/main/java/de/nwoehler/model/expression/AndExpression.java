package de.nwoehler.model.expression;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class AndExpression extends Expression {
    private Expression leftExpression;
    private Expression rightExpression;
}
