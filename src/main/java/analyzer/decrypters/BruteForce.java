package analyzer.decrypters;

import java.util.List;

import analyzer.model.Cipher;

public final class BruteForce {

    public String decryptByBruteForce(String encryptedText, List<Character> alphabet, boolean isNeedPrint) {
        Cipher cipher = new Cipher(alphabet);
        for (int key = 0; key < alphabet.size(); key++) {
            String decryptedText = cipher.decrypt(encryptedText, key);
            if (checkMostFrequen(decryptedText)) {
                System.err.println("Ключ: " + key);
                return decryptedText;
            }
        }
        return null;
    }

    private boolean checkMostFrequen(String decryptedText) {
        return decryptedText.contains(" и ")
                || decryptedText.contains(" в ")
                || decryptedText.contains(" с ");
    }
}
