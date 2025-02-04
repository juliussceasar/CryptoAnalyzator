import java.io.IOException;

public class BruteForce {

    public static String[] getPopularWords(String path) throws IOException {
        String str = FileManager.readFile(path);

        return str.split("\n");
    }

    public String decryptByBruteForce(String encryptedText, String[] popularWords) {
        int n = 0;
        int realShift;

        for (int i = 0; i < encryptedText.length(); i++) {
            String tryEncryptText = Cipher.decrypt(encryptedText, i);

            String[] tryEncryptTextArray = tryEncryptText.split(" ");

            for (int j = 0; j < tryEncryptTextArray.length; j++) {
                tryEncryptTextArray[j] = tryEncryptTextArray[j].trim()
                        .replaceAll("\\,", "").replaceAll("\\.", "");
                for (String popularWord : popularWords) {
                    if (tryEncryptTextArray[j].length() == popularWord.length()) {
                        if (tryEncryptTextArray[j].equalsIgnoreCase(popularWord)) {
                            n++;
                        }
                        if (n > 20) {
                            realShift = i;
                            return "shift is " + realShift;
                        }
                    }
                }
            }
        }
        return "not found";
    }


}