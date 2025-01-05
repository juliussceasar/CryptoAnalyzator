import java.io.File;
import java.nio.file.Path;

public class Validator {

    public boolean isValidKey(int key, char[] alphabet) {
        boolean isValid = key < alphabet.length;
        return isValid;
    }
    public boolean isFileExists(String filePath) {
        File file = Path.of(filePath).toFile();
        return file.exists();
    }
}
