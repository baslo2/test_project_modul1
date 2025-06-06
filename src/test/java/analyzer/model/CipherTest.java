package analyzer.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CipherTest {

    private static final String MSG = "привет медвед.";
    private static final String EXPECTED_ECRYPT = "рсйгёуанёегёе,";

    @Test
    void encryptTest() {
        Cipher cipher = new Cipher();
        Assertions.assertEquals(EXPECTED_ECRYPT, cipher.encrypt(MSG, 1));
    }

    @Test
    void decryptTest() {
        Cipher cipher = new Cipher();
        Assertions.assertEquals(MSG, cipher.decrypt(EXPECTED_ECRYPT, 1));
    }
}
