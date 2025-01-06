import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class MainApp {
    static String englishTextSample = "C:\\Users\\Нур89\\IdeaProjects\\cryptoanalyzer\\src\\EnglishText.txt";
    static String commonEnglish100words = "C:\\Users\\Нур89\\IdeaProjects\\cryptoanalyzer\\src\\file100.txt";
    static String fileEncrypted = "C:\\Users\\Нур89\\IdeaProjects\\cryptoanalyzer\\src\\fileToEnd.txt";

    static Scanner scanner = new Scanner(System.in);
    static FileManager fm = new FileManager();
    static Cipher cipher = new Cipher();
    static Validator vd = new Validator();
    static BruteForce bf = new BruteForce();

    public static void main(String[] args)  {
        try {
            if (args.length != 0) {
                choiceEntered(args);
            }
            while (true) {
                showMenu();
                choiceEntered(validInputMenu());
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static void showMenu() {
        System.out.println("What do you want to do?");
        System.out.println("1. Encrypt a file. \n> 1 + shift");
        System.out.println("2. Decrypt a file. " +
                "\n(Key is the same for encryption/decryption.) " +
                "\n> 2 + shift");
        System.out.println("3. Bruteforce.");
        System.out.println("4. Change files");
        System.out.println("0. Exit.");
    }

    public static void changeFiles() throws IOException {
        System.out.println("Enter path to English text sample: ");
        englishTextSample = scanner.nextLine();
        System.out.println("Enter path to common English 100 words: ");
        commonEnglish100words = scanner.nextLine();
        System.out.println("Enter path to a file with encrypted text: ");
        fileEncrypted = scanner.nextLine();
        String[] files = new String[]{englishTextSample, commonEnglish100words, fileEncrypted};
        for (String file : files) {
            //Files.createFile(Path.of(file));
            if (!Files.isRegularFile(Path.of(file))) {
                Files.createFile(Path.of(file));
            }
        }
        System.out.println("New files were updated.");
    }



    public static void choiceEntered(String[] answStr) throws IOException {

        String[] popularWords = bf.getPopularWords(commonEnglish100words);

        int shift;

        if (vd.isFileExists(englishTextSample) &&
                vd.isFileExists(commonEnglish100words) &&
                vd.isFileExists(fileEncrypted)) {
            switch (Integer.parseInt(answStr[0])) {
                case 1:
                    String workingText = fm.readFile(englishTextSample);

                    shift = Integer.parseInt(answStr[1]);
                    if (vd.isValidKey(shift, cipher.getAlphabet())) {
                        String resWorkingText = cipher.encrypt(workingText, shift);
                        System.out.println(resWorkingText);
                        fm.writeFile(resWorkingText, fileEncrypted);
                    } else {
                        System.out.println("Key is invalid.");
                    }
                    break;

                case 2:
                    String workingText2 = fm.readFile(fileEncrypted);

                    shift = Integer.parseInt(answStr[1]);
                    if (vd.isValidKey(shift, cipher.getAlphabet())) {
                        String resWorkingText2 = cipher.decrypt(workingText2, shift);
                        System.out.println(resWorkingText2);
                        fm.writeFile(resWorkingText2, fileEncrypted);
                    } else {
                        System.out.println("Key is invalid.");
                    }
                    break;
                case 3:
                    String workingText3 = fm.readFile(fileEncrypted);

                    System.out.println(bf.decryptByBruteForce(workingText3, popularWords));

                    break;
                case 4:
                    changeFiles();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        } else {
            System.out.println("File is invalid!");
        }
    }

    public static String[] validInputMenu() {
        String[] answStr = scanner.nextLine().split(" ");
        while (!String.join(" ", answStr).strip().matches("(^[034]$)|(^[1-2] \\d*$)")) {
            if (answStr.length == 0 || !answStr[0].matches("\\d")) {
                System.out.println("Enter a number from 0 to 3");
            }
            else if ((Integer.parseInt(answStr[0]) == 1 || Integer.parseInt(answStr[0]) == 2) && answStr.length != 2) {
                System.out.println("Please, enter the menu number + shift");
            } else {
                System.out.println("Incorrect input!");
            }
            answStr = scanner.nextLine().split(" ");

        }
        return answStr;
    }
}