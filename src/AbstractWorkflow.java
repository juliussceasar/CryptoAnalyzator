import java.io.IOException;

public abstract class AbstractWorkflow {
    protected String content;
    protected void lifecycle(String filePath, String text, int shift, String content, String fileEndPath) throws IOException {
        readFile(filePath);
        execute(text, shift);
        writeFile(content, fileEndPath);
    }

    protected String getContentCiphered() {
            return content;
    }

    protected void setContentCiphered(String content) {
        this.content = content;
    }


    protected void readFile(String filePath) throws IOException {
        content = FileManager.readFile(filePath);
        setContentCiphered(content);
    };

    abstract void execute(String contentRaw, int shift);

    protected void writeFile(String content, String fileEndPath) throws IOException {
        FileManager.writeFile(content, fileEndPath);
    };
}
