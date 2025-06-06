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
        fillResult(result, text, shift);
        return String.valueOf(result);

    }

    public String decrypt(String encryptedText, int shift) {
        char[] result = new char[encryptedText.length()];
        fillResult(result, encryptedText, -shift);
        return String.valueOf(result);
    }

    private void fillResult(char[] result, String text, int shift) {
        int index = 0;
        for (char i : text.toLowerCase().toCharArray()) {
            int j = alphabet.indexOf(i);
            int temp = getIndex(j, shift);
            result[index] = alphabet.get(temp);
            index++;
        }
    }

    private int getIndex(int alphabetIndex, int shift) {
        return (alphabet.size() + (alphabetIndex + shift)) % alphabet.size();
    }
}
