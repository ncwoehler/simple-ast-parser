package de.nwoehler.model.statement;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class StatementList extends Statement {
    private List<Statement> statements = new ArrayList<>();
}
