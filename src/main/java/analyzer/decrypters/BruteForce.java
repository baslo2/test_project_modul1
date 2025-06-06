package analyzer.decrypters;

import java.util.List;

import analyzer.model.Cipher;

public final class BruteForce {

    public String decryptByBruteForce(String encryptedText, List<Character> alphabet, boolean isNeedPrint) {
        Cipher cipher = new Cipher(alphabet);
        StringBuilder result = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        for (int key = 0; key < alphabet.size(); key++) {
            String decryptedText = cipher.decrypt(encryptedText, key);
            sb.append("Ключ ").append(key).append(": \n")
                    .append(decryptedText).append("\n");
            result.append(sb);
            if (isNeedPrint) {
                System.out.println(sb);
            }
            sb.setLength(0);
        }
        result.setLength(result.length() - 1);
        return result.toString();
    }
}
