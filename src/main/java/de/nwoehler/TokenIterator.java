package de.nwoehler;

import lombok.AllArgsConstructor;

import java.util.Iterator;

@AllArgsConstructor
public class TokenIterator {

    private Iterator<String> iterator;
    private int line;

    /**
     * Reads the next available token. Fails if no further token is available.
     *
     * @return the next token
     */
    public String nextToken() {
        if (!iterator.hasNext()) {
            throw new IllegalArgumentException(getErrorMessage("Unexpected end of statement."));
        }
        return iterator.next();
    }

    /**
     * For every provided token it reads the next available tolen and checks whether they match the expected token.
     * Throws an error in case it does not match.
     *
     * @param expected the token(s) that are expected next
     */
    public void expectToken(String... expected) {
        for(String expectedToken : expected) {
            var next = nextToken();
            if (!expectedToken.equalsIgnoreCase(next)) {
                throw new IllegalArgumentException(
                        getErrorMessage("Expected " + expectedToken + " but got " + next)
                );
            }
        }
    }

    /**
     * Checks that no further token is available. Fails in case another token is available.
     */
    public void expectEnd() {
        if (iterator.hasNext()) {
            throw new IllegalArgumentException(
                    getErrorMessage("Expected statement end but got " + iterator.next())
            );
        }
    }

    public String getErrorMessage() {
        return getErrorMessage("");
    }

    String getErrorMessage(String reason) {
        return "Malformed statement at line " + line + ". " + reason;
    }

}
