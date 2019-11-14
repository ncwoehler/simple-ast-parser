package de.nwoehler;

import de.nwoehler.model.statement.StatementList;
import lombok.Data;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.concurrent.Callable;

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

        var statements = StatementParser.parseStatementList(sqlInput);

        // TODO write output to stdout

        return statements;
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
