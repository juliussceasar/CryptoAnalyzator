import java.io.IOException;

public class BruteForce {

    public String[] getPopularWords(String path) throws IOException {
        FileManager fm = new FileManager();
        String str = fm.readFile(path);

        return str.split("\n");
    }

    public String decryptByBruteForce(String encryptedText, String[] popularWords) {
        int n = 0;
        int realShift;
        Cipher cipher = new Cipher();

        for (int i = 0; i < encryptedText.length(); i++) {
            String tryEncryptText = cipher.decrypt(encryptedText, i);

            String[] tryEncryptTextArray = tryEncryptText.split(" ");

            for (int j = 0; j < tryEncryptTextArray.length; j++) {
                tryEncryptTextArray[j] = tryEncryptTextArray[j].trim()
                        .replaceAll("\\,", "").replaceAll("\\.", "");
                for (int k = 0; k < popularWords.length; k++) {
                    if (tryEncryptTextArray[j].length() == popularWords[k].length()) {
                        if (tryEncryptTextArray[j].equalsIgnoreCase(popularWords[k])) {
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