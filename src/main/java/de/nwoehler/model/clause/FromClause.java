package de.nwoehler.model.clause;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FromClause {
    // assume table reference to be atomic
    // could be split into table and database for statements such as "database.logs"
    private String table;
}
