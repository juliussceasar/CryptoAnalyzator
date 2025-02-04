import java.io.IOException;

public class DecryptWorkflow extends AbstractWorkflow {
    @Override
    void execute(String text, int shift) {
        content = Cipher.decrypt(getContentCiphered(), shift);
        System.out.println(content);
    }

}
