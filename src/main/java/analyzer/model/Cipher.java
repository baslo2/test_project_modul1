package analyzer.model;

import java.util.List;

import analyzer.utils.Utils;

public final class Cipher {
    private final List<Character> alphabet;

    public Cipher(List<Character> alphabet) {
        this.alphabet = alphabet;
    }

    public Cipher() {
        this.alphabet = Utils.ABC.RUSSIAN_ABC;
    }

    public String encrypt(String text, int shift) {
        char[] result = new char[text.length()];
        int index = 0;
        for (char i : text.toLowerCase().toCharArray()) {
            int j = alphabet.indexOf(i);
            int temp = 0;
            if (0 > shift) {
                temp = alphabet.size() + ((shift - j) % alphabet.size());
            } else {
                temp = (j - shift) % alphabet.size();
            }
            result[index] = alphabet.get(temp);
            index++;
        }

        return String.valueOf(result);

    }

    public String decrypt(String encryptedText, int shift) {
        char[] result = new char[encryptedText.length()];
        int index = 0;
        for (char i : encryptedText.toLowerCase().toCharArray()) {
            int j = alphabet.indexOf(i);
            int temp = 0;
            if (j < shift) {
                temp = alphabet.size() - ((shift - j) % alphabet.size());
            } else {
                temp = (j - shift) % alphabet.size();
            }
            result[index] = alphabet.get(temp);
            index++;
        }

        return String.valueOf(result);
    }
}
