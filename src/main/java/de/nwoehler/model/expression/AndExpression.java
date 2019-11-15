package de.nwoehler.model.expression;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AndExpression extends Expression {
    private Expression leftExpression;
    private Expression rightExpression;
}
