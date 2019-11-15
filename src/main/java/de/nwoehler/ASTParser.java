package de.nwoehler;

import com.google.common.base.Splitter;
import de.nwoehler.model.statement.Statement;
import de.nwoehler.model.statement.StatementList;
import de.nwoehler.parser.Statements;
import lombok.Data;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;

@Data
@Command(name = "parseSQL", mixinStandardHelpOptions = true, version = "parse 0.1",
        description = "Parses the AST from an SQL file and prints it to the command line")
public class ASTParser implements Callable<StatementList> {

    @CommandLine.Option(names = { "-f", "--file" } , description = "The SQL file to parse")
    private File sqlFile;

    @CommandLine.Option(names = { "-t", "--text" } , description = "The SQL commands to parse")
    private String text;

    @Override
    public StatementList call() throws Exception {
        if (sqlFile == null && text == null) {
            throw new IllegalArgumentException("Neither SQL file nor text input are defined");
        }
        String sqlInput = getSQLInput();
        if (sqlInput.strip().isBlank()) {
            throw new IllegalArgumentException("Failed to parse AST. Provided input is empty.");
        }

        var statements = parse(sqlInput);

        // TODO write output to stdout

        return statements;
    }

    private StatementList parse(String sqlInput) {
        StatementList statementList = new StatementList();

        // Let's keep it simple, assume each SQL statement ends with a ;
        var statementLines = Splitter.on(";").trimResults().omitEmptyStrings().split(sqlInput);
        int line = 1;
        for (String statementLine : statementLines) {
            // Split each SQL statement into its tokens
            var tokens = Splitter.on(Pattern.compile("\\s+")).split(statementLine);
            var tokenIterator = tokens.iterator();
            Statement nextStatement = Statements.parse(tokenIterator, line);
            if(nextStatement != null) {
                statementList.getStatements().add(nextStatement);
            }
            line++;
        }
        return statementList;
    }

    private String getSQLInput() throws IOException {
        // prefer file input over text input
        if (sqlFile != null) {
            byte[] fileContents = Files.readAllBytes(sqlFile.toPath());
            return new String(fileContents, StandardCharsets.UTF_8);
        }
        return text;
    }
}
