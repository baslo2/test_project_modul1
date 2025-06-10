package analyzer.decrypters;

import java.util.Arrays;
import java.util.List;

import analyzer.model.Cipher;

public final class BruteForce {

    private final List<String> Most_Frequen = Arrays.asList(" и ", " в ", " с ", " у ", " я ", " а ", " о ",
            " на ", " не ", " но ", " по ", " за ", " из ", " от ", " до ", " к ", " со ", " то ", " бы ", " же ",
            " ли ", " ну ", " ибо ", " или ", " для ", " над ", " под ", " без ", " раз ", " он ", " она ", " оно ",
            " мы ", " вы ", " они ", " это ", " вот ", " даже ", " уж ");

    public String decryptByBruteForce(String encryptedText, List<Character> alphabet, boolean isNeedPrint) {
        Cipher cipher = new Cipher(alphabet);
        for (int key = 0; key < alphabet.size(); key++) {
            String decryptedText = cipher.decrypt(encryptedText, key);
            if (isDecryptedText(decryptedText)) {
                System.err.println("Ключ: " + key);
                return decryptedText;
            }
        }
        return null;
    }

    private boolean isDecryptedText(String decryptedText) {
        int expectedMatchCount = decryptedText.length() / 20;
        int matchCounter = 0;
        for (String word : Most_Frequen) {
            matchCounter += decryptedText.split(word).length;
        }
        return expectedMatchCount <= matchCounter;
    }
}
