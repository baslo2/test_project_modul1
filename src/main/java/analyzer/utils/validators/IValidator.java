package analyzer.utils.validators;

import java.io.File;
import java.util.List;

public interface IValidator {

    boolean isValidKey(int key);

    default boolean isValidKey(int key, List<Character> alphabet) {
        return key >= 0 && key < alphabet.size();
    }

    default boolean isFileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}
