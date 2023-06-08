package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class MenuUtil {

    /**
     * Prompts the user to press 'e' to return to menu
     */
    public static void promptReturnToMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter 'e' to return to menu. ");
            String input = scanner.nextLine();
            input = input.toLowerCase();
            if (input.equals("e")) {
                break;
            }
        }
    }

    /**
     * Asks user for the prompt they want for the question
     *
     * @param questionType The type of question to create a prompt for
     * @return The user input
     */
    public static String createQuestionPrompt(String questionType) {
        System.out.print("Enter the prompt for your " + questionType + " question: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        /*
        if (!(input.charAt(input.length() - 1) == '?')) {
            input = input + "?";
        }

         */
        return input;
    }

    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    public static int promptNumberOfAnswers(String questionType, int numberOfAnswersLimit) {
        Scanner scanner = new Scanner(System.in);
        String input1;
        int input2;

        while (true) {
            System.out.print("Would you like your " + questionType + " question to have multiple answers/responses?(y/n)");
            input1 = scanner.nextLine();
            input1 = input1.toLowerCase();
            if (input1.equals("y")) {
                while (true) {
                    System.out.print("Enter the number of answers/responses for this" +
                            " question (limit is " + numberOfAnswersLimit + "): ");
                    input2 = Input.readIntInRange(2, numberOfAnswersLimit);
                    return input2;
                }
            } else if (input1.equals("n")) {
                return 1;
            }
        }

    }

    public static String readLetterInAlphabetRange(int range) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        while (true) {
            String letter = getUserInput();
            letter = letter.toUpperCase();
            if (letter.length() == 1 && alphabet.contains(letter) && alphabet.indexOf(letter) < range) {
                return letter;
            } else {
                System.out.println("Please enter a valid letter.");
            }
        }
    }

    public static String readDate() {
        while (true) {
            String input = getUserInput();
            if (isValidDate(input)) {
                return input;
            } else {
                System.out.println("Please enter a valid date in 'MM/DD/YYYY' format.");
            }
        }
    }

    /**
     * Check if a string is a valid date in MM/dd/yyyy format
     * <p>
     * Inspired by: Chandra Prakash
     * https://www.baeldung.com/java-string-valid-date
     *
     * @param date The string to validate
     * @return boolean
     */
    public static boolean isValidDate(String date) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static String getTestPath() {
        String testPath;
        try {
            testPath = FileUtils.listAndPickFileFromDir(FSConfig.testDir);
            return testPath;
        } catch (IllegalStateException e) {
            System.out.println("No tests to grade. Create, save, and take a test to grade it.");
            MenuUtil.promptReturnToMenu();
            return null;
        }
    }

    public static String getTestResponsePath(String testResponseDirPath) {
        String testResponsePath;
        try {
            testResponsePath = FileUtils.listAndPickFileFromDir(testResponseDirPath);
            return testResponsePath;
        } catch (IllegalStateException e) {
            System.out.println("No test responses to grade for this test. Take the test to generate a response.");
            MenuUtil.promptReturnToMenu();
            return null;
        }

    }
}
