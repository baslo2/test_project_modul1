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
        return fillResult(text, shift);

    }

    public String decrypt(String encryptedText, int shift) {
        return fillResult(encryptedText, -shift);
    }

    private String fillResult(String text, int shift) {
        char[] result = new char[text.length()];
        int index = 0;
        for (char i : text.toLowerCase().toCharArray()) {
            int j = alphabet.indexOf(i);
            int temp = getIndex(j, shift);
            result[index] = alphabet.get(temp);
            index++;
        }
        return String.valueOf(result);
    }

    private int getIndex(int alphabetIndex, int shift) {
        return (alphabet.size() + (alphabetIndex + shift)) % alphabet.size();
    }
}
