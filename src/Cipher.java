public class Cipher {
    private static char[] alphabet = ("abcdefghijklmnopqrstuvwxyz" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЧШЩЪЫЬЭЮЯ" +
            "абвгдеёжзийклмнопрстуфхчшщъыьэюя" +
            ".,-:;! ").toCharArray();

    public void changeCipher(char[] alphabet) {
        Cipher.alphabet = alphabet;
    }

    public static char[] getAlphabet() {
        return alphabet;
    }

    public static String encrypt(String text, int shift) {
        char[] textCh = text.toCharArray();

        for (int i = 0; i < text.length(); i++) {
            int variable = textCh[i];
            if (textCh[i] == '\n' || textCh[i] == '\r') {
                continue;
            }
            for (int j = 0; j < alphabet.length; j++) {
                if (alphabet[j] == variable) {
                    int realShift = j + shift;
                    if (realShift >= alphabet.length) {
                        realShift = realShift % alphabet.length;
                    }
                    while (realShift < 0) {
                        realShift = alphabet.length + realShift;
                    }
                    textCh[i] = alphabet[realShift];
                    break;
                }

            }
        }
        return String.valueOf(textCh);
    }

    public static String decrypt(String encryptedText, int shift) {
        encryptedText = encrypt(encryptedText, -shift);
        return encryptedText;
    }
}
