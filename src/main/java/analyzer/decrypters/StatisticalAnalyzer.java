package analyzer.decrypters;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class StatisticalAnalyzer {

    public int findMostLikelyShift(String encryptedText, List<Character> alphabet, String representativeText) {
        char mostFrequentEncrypted = findMostFrequentChar(encryptedText);
        char mostFrequentOriginal = findMostFrequentChar(representativeText);

        int shift = alphabet.indexOf(mostFrequentEncrypted) - alphabet.indexOf(mostFrequentOriginal);
        return shift >= 0 ? shift : shift + alphabet.size();
    }

    private char findMostFrequentChar(String text) {
        // Логика для нахождения самого частого символа
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        return Collections
                .max(frequencyMap.entrySet(), Map.Entry.comparingByValue())
                .getKey();
    }
}
