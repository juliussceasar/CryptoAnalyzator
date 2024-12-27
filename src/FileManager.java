import java.io.*;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class FileManager {
    public String readFile(String filePath) throws IOException {

        Path file = Path.of(filePath);


        StringBuilder content;
        try (FileChannel fileChannel = FileChannel.open(file, StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024); // Create a buffer of 1 KB
            content = new StringBuilder();

            while (fileChannel.read(buffer) > 0) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    //content.append((char) buffer.get()); // Read byte-by-byte as characters
                    content.append(StandardCharsets.UTF_8.decode(buffer));

                }
                buffer.clear();

            }
            return content.toString();
        }
    }

    public void writeFile(String content, String filePath) throws IOException {
        File file = Path.of(filePath).toFile();

        try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
            byte[] buffer = new byte[1024];


            buffer = content.getBytes(StandardCharsets.UTF_8);
            int bytesRead;
            bos.write(buffer, 0, buffer.length);


        }
    }
}