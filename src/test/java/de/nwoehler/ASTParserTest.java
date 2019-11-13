package de.nwoehler;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ASTParserTest {

    @Test
    public void testASTParsing() throws Exception {
        ASTParser parser = new ASTParser();
        parser.text = "USE databse1";

        String result = parser.call();
        assertThat(result).isEqualTo(parser.text);
    }

}