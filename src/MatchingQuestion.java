import utils.Input;
import utils.MenuUtil;
import utils.Validation;

import java.util.ArrayList;

public class MatchingQuestion extends Question {

    private static final long serialVersionUID = -2555617957306036930L;
    //private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int MATCH_LIMIT = 26;
    protected int numberOfMatches;
    private ArrayList<String> left;
    private ArrayList<String> right;

    public MatchingQuestion() {
        left = new ArrayList<>();
        right = new ArrayList<>();

        questionType = "Matching";
    }

    public int getNumberOfMatches() {
        return numberOfMatches;
    }

    @Override
    public void create() {
        prompt = MenuUtil.createQuestionPrompt(questionType);
        System.out.print("Enter the number of matches for your " + questionType +
                " question(limit is " + MATCH_LIMIT + "): ");
        numberOfMatches = Input.readIntInRange(1, MATCH_LIMIT);
        for (int i = 0; i < numberOfMatches; i++) {
            System.out.print("Enter row #" + (i + 1) + "(LEFT column): ");
            String input1 = MenuUtil.getUserInput();
            System.out.print("Enter row #" + (i + 1) + "(RIGHT column): ");
            String input2 = MenuUtil.getUserInput();
            left.add(input1);
            right.add(input2);
        }
    }

    @Override
    public void display(int questionNumber) {
        System.out.print((questionNumber + 1) + ") " + prompt + "\n");
        displayMatches();
    }

    public void displayMatches() {
        for (int i = 0; i < numberOfMatches; i++) {
            System.out.println(String.format("%-25.25s  %-25.25s", ALPHABET.charAt(i) + ") " + left.get(i),
                    (i + 1) + ") " + right.get(i)));
        }
        System.out.print("\n");
    }

    @Override
    public void modify() {
        String input;
        int input2;

        modifyPrompt();

        while (true) {
            displayMatches();
            System.out.println("Would you like to modify the matching choices?(y/n)");
            input = MenuUtil.getUserInput();
            input = input.toLowerCase();

            if (input.equals("y")) {
                System.out.println("Which choice would you like to modify(enter the corresponding letter or number)?");
                while (true) {
                    input = MenuUtil.getUserInput();
                    input = input.toUpperCase();

                    if (Validation.isInt(input)) {
                        input2 = Integer.parseInt(input);
                        if (input2 > 0 && input2 <= left.size()) {
                            System.out.print("Enter a new choice: ");
                            input = MenuUtil.getUserInput();
                            right.set(input2 - 1, input);
                            break;
                        } else {
                            System.out.println("Please enter a valid letter or number.");
                            continue;
                        }
                    }


                    if (input.length() == 1 && ALPHABET.contains(input) && ALPHABET.indexOf(input) < left.size()) {
                        input2 = ALPHABET.indexOf(input);

                        System.out.print("Enter a new choice: ");
                        input = MenuUtil.getUserInput();
                        left.set(input2, input);
                        break;
                    } else {
                        System.out.println("Please enter a valid letter or number.");
                    }

                }
            } else if (input.equals("n")) {
                break;
            }
        }
    }

    @Override
    public void take() {
        String input;
        String left;
        String right;
        int intRight;

        for (int i = 0; i < this.left.size(); i++) {
            while (true) {
                input = MenuUtil.getUserInput();
                input = input.toUpperCase();

                if (responseCorrectAnswer.getUserAnswer().contains(input)) {
                    System.out.println("Choice already selected. Please select another choice.");
                    continue;
                }

                if (input.length() < 2) {
                    System.out.println("Please enter a valid input(Ex. A1, B1, A2, or B2, etc).");
                    continue;
                }

                left = input.substring(0, 1);
                right = input.substring(1);

                if (Validation.isInt(right)) {
                    intRight = Integer.parseInt(input.substring(1));
                    if (intRight > 0 && intRight <= this.left.size()) {

                        if (ALPHABET.contains(left) && ALPHABET.indexOf(left) < this.left.size()) {
                            break;
                        } else {
                            System.out.println("Please enter a valid input(Ex. A1, B1, A2, or B2, etc).");
                            continue;
                        }

                    } else {
                        System.out.println("Please enter a valid input(Ex. A1, B1, A2, or B2, etc).");
                        continue;
                    }
                } else {
                    System.out.println("Please enter a valid input(Ex. A1, B1, A2, or B2, etc).");
                    continue;
                }

            }

            responseCorrectAnswer.addAnswer(input);
        }
    }
}
