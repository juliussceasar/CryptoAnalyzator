import java.io.IOException;

public abstract class AbstractWorkflow {
    protected String content;

    protected String getContentCiphered() {
        return content;
    }

    protected void setContentCiphered(String content) {
        this.content = content;
    }


    protected void readFile(String filePath) throws IOException {
        content = FileManager.readFile(filePath);
        setContentCiphered(content);
    }

    abstract void execute(String contentRaw, int shift);

    protected void writeFile(String fileEndPath) throws IOException {
        FileManager.writeFile(getContentCiphered(), fileEndPath);
    }
}
