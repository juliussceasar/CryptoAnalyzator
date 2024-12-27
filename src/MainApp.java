import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class MainApp {
    static final String englishText = "C:\\Users\\Нур89\\IdeaProjects\\cryptoanalyzer\\src\\EnglishText.txt";
    static final String fileCommonEng = "C:\\Users\\Нур89\\IdeaProjects\\cryptoanalyzer\\src\\file100.txt";
    static final String fileToEnd = "C:\\Users\\Нур89\\IdeaProjects\\cryptoanalyzer\\src\\fileToEnd.txt";
    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        mainMenu();
    }

    public static void mainMenu() throws IOException {

        System.out.println("What do you want to do?");
        System.out.println("1. Encrypt a file. \n> 1 + shift");
        System.out.println("2. Decrypt a file. \n> 2 + shift");
        System.out.println("3. Bruteforce.");
        System.out.println("0. Exit.");

        String[] answStr = scanner.nextLine().split(" ");

        FileManager fm = new FileManager();
        Cipher cipher = new Cipher();
        Validator vd = new Validator();
        BruteForce bf = new BruteForce();
        String[] popularWords = bf.getPopularWords();

        validInput(answStr);
        int shift;

        if (!answStr[0].isEmpty() && vd.isFileExists(fileCommonEng)) {
            switch (Integer.parseInt(answStr[0])) {
                case 1:
                    String workingText = fm.readFile(englishText);

                    shift = Integer.parseInt(answStr[1]);
                    String resWorkingText = cipher.encrypt(workingText, shift);
                    System.out.println(resWorkingText);
                    fm.writeFile(resWorkingText, fileToEnd);

                    mainMenu();
                    break;
                case 2:
                    String workingText2 = fm.readFile(fileToEnd);

                    shift = Integer.parseInt(answStr[1]);
                    String resWorkingText2 = cipher.decrypt(workingText2, shift);
                    System.out.println(resWorkingText2);
                    fm.writeFile(resWorkingText2, fileToEnd);

                    mainMenu();
                    break;
                case 3:
                    String workingText3 = fm.readFile(fileToEnd);

                    System.out.println(bf.decryptByBruteForce(workingText3, popularWords));

                    mainMenu();
                    break;

                case 0:
                    System.exit(0);
                    break;
                default:
                    mainMenu();
                    break;
            }
        } else {
            mainMenu();
        }
    }

    public static void validInput(String[] answer) throws IOException {
        if (answer.length == 0 || Objects.equals(answer[0], "")) {
            System.out.println("Enter a number from 0 to 3");
            mainMenu();
        }


        while (answer.length != 2 && (Integer.parseInt(answer[0]) == 1 || Integer.parseInt(answer[0]) == 2)) {
            System.out.println("Please, enter the menu number + shift");
            answer = scanner.nextLine().split(" ");
        }


    }
}