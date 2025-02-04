import java.io.IOException;

public class BruteForce {

    public static String[] getPopularWords(String path) throws IOException {

        return new String[]{FileManager.readFile(path)};
    }

    public String decryptByBruteForce(String encryptedText, String[] popularWords) {
        int n = 0;
        int realShift;

        for (int i = 0; i < encryptedText.length(); i++) {
            String tryEncryptText = Cipher.decrypt(encryptedText, i);

            String[] tryEncryptTextArray = tryEncryptText.split(" ");
            String[] popularWordsArray = popularWords[0].split(", ");
            for (int j = 0; j < tryEncryptTextArray.length; j++) {
                tryEncryptTextArray[j] = tryEncryptTextArray[j].trim()
                        .replaceAll(",", "").replaceAll("\\.", "");
                for (String s : popularWordsArray) {
                    if (tryEncryptTextArray[j].length() == s.length()) {
                        if (tryEncryptTextArray[j].equalsIgnoreCase(s)) {
                            n++;
                        }
                        if (n > 10) {
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