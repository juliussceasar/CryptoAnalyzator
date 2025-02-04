import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileManager {


    public static String readFile(String filePath) throws IOException {

        Path file = Path.of(filePath);

        List<String> content = Files.readAllLines(file);
        return String.join(", ", content);

    }

    public static void writeFile(String content, String filePath) throws IOException {
        Path path = Path.of(filePath);

        Files.write(path, content.getBytes());
    }
}