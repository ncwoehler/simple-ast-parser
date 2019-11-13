import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.concurrent.Callable;

@Command(name = "parseSQL", mixinStandardHelpOptions = true, version = "parse 0.1",
        description = "Parses the AST from an SQL file and prints it to the command line")
public class ASTParser implements Callable<String> {

    @CommandLine.Option(names = { "-f", "--file" } , description = "The SQL file to parse")
    File sqlFile;

    @CommandLine.Option(names = { "-t", "--text" } , description = "The SQL commands to parse")
    String text;

    public static void main(String... args) {
        int exitCode = new CommandLine(new ASTParser()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public String call() throws Exception {
        if (sqlFile == null && text == null) {
            throw new IllegalArgumentException("Neither SQL file nor text input are defined");
        }
        String sql = getSQLInput();
        return sql;
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
