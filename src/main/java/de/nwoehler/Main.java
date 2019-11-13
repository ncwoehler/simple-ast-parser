package de.nwoehler;

import picocli.CommandLine;

public class Main {

    public static void main(String... args) {
        int exitCode = new CommandLine(new ASTParser()).execute(args);
        System.exit(exitCode);
    }

}
