package analyzer.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FileManagerTest {

    @Test
    void testReadFile() {
        String text = new FileManager()
                .readFile("src/test/resources/Origin.txt");
        Assertions.assertNotNull(text);
        Assertions.assertEquals(TestUtils.EXPECTED_TEXT, text);
    }
}
