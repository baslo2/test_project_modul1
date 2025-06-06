package analyzer.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FileManager {

    public String readFile(String filePath) {
        try {
            return Files.readString(Path.of(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeFile(String content, String filePath) {
        try {
            Files.writeString(Path.of(filePath), content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
