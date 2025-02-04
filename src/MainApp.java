import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class MainApp {
    static String englishTextSample = "src/EnglishText.txt";
    static String commonEnglish100words = "src/file100.txt";
    static String fileEncrypted = "src/fileToEnd.txt";

    static Scanner scanner = new Scanner(System.in);
    static Validator validator = new Validator();
    static BruteForce bruteForce = new BruteForce();
    static AbstractWorkflow encryptWorkflow = new EncryptWorkflow();
    static AbstractWorkflow decryptWorkflow = new DecryptWorkflow();

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
        System.out.println("""
                What do you want to do?
                1. Encrypt a file.
                > 1 + shift
                2. Decrypt a file.
                (Key is the same for encryption/decryption.)
                > 2 + shift
                3. Bruteforce.
                4. Change files.
                0. Exit.""");
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
            Path pathOfFile = Path.of(file);
            if (!Files.isRegularFile(pathOfFile)) {
                Files.createFile(pathOfFile);
            }
        }
        System.out.println("New files were updated.");
    }



    public static void choiceEntered(String[] choice) throws IOException {
        String files[] = new String[] {englishTextSample, commonEnglish100words, fileEncrypted};
        boolean isValidatedFiles = false;
        for (String file : files) {
            isValidatedFiles = true;
            if (!Path.of(file).toFile().exists()) {
                System.out.println(file + " does not seem to exist. Please, change files.");
                isValidatedFiles = false;
                break;
            }
        }

        String[] popularWords = BruteForce.getPopularWords(commonEnglish100words);
        int action = Integer.parseInt(choice[0]);
        int shift = (choice.length == 2) ? Integer.parseInt(choice[1]) : 0;

        boolean isValidatedShift = validator.isValidKey(shift, Cipher.getAlphabet());

        if (!isValidatedFiles) {
            System.out.println("File is invalid.");
        } else if (!isValidatedShift) {
            System.out.println("Shift is not in the range of an alphabet. Change shift or alphabet.");
        }

        switch (action) {
            case 1:
                encryptWorkflow.readFile(englishTextSample);

                encryptWorkflow.execute(englishTextSample, shift);

                encryptWorkflow.writeFile(encryptWorkflow.getContentCiphered(), fileEncrypted);

                break;
            case 2:
                decryptWorkflow.readFile(fileEncrypted);

                decryptWorkflow.execute(fileEncrypted, shift);

                decryptWorkflow.writeFile(encryptWorkflow.getContentCiphered(), fileEncrypted);

                break;
            case 3:
                String workingText3 = FileManager.readFile(fileEncrypted);

                System.out.println(bruteForce.decryptByBruteForce(workingText3, popularWords));

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