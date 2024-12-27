import java.io.IOException;

public class BruteForce {
    static final String fileCommonEng = "C:\\Users\\Нур89\\IdeaProjects\\cryptoanalyzer\\src\\file100.txt";


    public String[] getPopularWords() throws IOException {
        FileManager fm = new FileManager();
        String str = fm.readFile(fileCommonEng);

        String[] popularWords = str.split("\n");

        return popularWords;
    }

    public String decryptByBruteForce(String encryptedText, String[] popularWords) {
        int n = 0;
        int realShift = 0;
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
                            System.out.println(realShift + " " + i);
                            return "shift is " + realShift;
                        }
                    }
                }
            }
        }
        return "not found";
    }


}