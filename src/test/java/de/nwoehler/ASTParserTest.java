import de.nwoehler.ASTParser;
import org.junit.jupiter.api.Test;

class ASTParserTest {

    @Test
    public void testASTParsing() throws Exception {
        ASTParser parser = new ASTParser();
        parser = "USE databse1";

        String call = parser.call();
    }

}