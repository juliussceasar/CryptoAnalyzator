public class EncryptWorkflow extends AbstractWorkflow {
    @Override
    void execute(String text, int shift) {
        content = Cipher.encrypt(getContentCiphered(), shift);
        System.out.println(content);
    }

}

