import utils.Input;
import utils.MenuUtil;

import java.util.ArrayList;

public class MultipleChoiceQuestion extends Question {

    private static final long serialVersionUID = 4451117162226838649L;
    //private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int CHOICE_LIMIT = 26;
    protected int numberOfChoices;
    protected ArrayList<String> choices;

    public MultipleChoiceQuestion() {
        choices = new ArrayList<>();
        questionType = "Multiple Choice";
    }

    public int getNumberOfChoices() {
        return numberOfChoices;
    }

    @Override
    public void create() {
        numberOfAnswers = MenuUtil.promptNumberOfAnswers(questionType, NUMBER_OF_ANSWERS_LIMIT);
        prompt = MenuUtil.createQuestionPrompt(questionType);
        createChoices();
    }

    public void createChoices() {
        System.out.print("Enter the number of choices for your " + questionType +
                " question(limit is " + CHOICE_LIMIT + "): ");
        numberOfChoices = Input.readIntInRange(numberOfAnswers, CHOICE_LIMIT);

        for (int i = 0; i < numberOfChoices; i++) {
            System.out.print("Enter choice #" + (i + 1) + ": ");
            String input = MenuUtil.getUserInput();
            choices.add(input);
        }
    }

    @Override
    public void display(int questionNumber) {
        System.out.println((questionNumber + 1) + ") " + prompt);
        if (numberOfAnswers > 1) {
            System.out.println("Please select " + numberOfAnswers + " choices(enter them one by one).");
        }
        displayChoices();
    }

    public void displayChoices() {
        ArrayList<String> displayContent = new ArrayList<>();
        for (int i = 0; i < numberOfChoices; i++) {
            displayContent.add(ALPHABET.charAt(i) + ") " + choices.get(i));
        }
        System.out.println(String.join("    ", displayContent) + "\n");
    }

    @Override
    public void modify() {
        String input;
        int input2;

        modifyPrompt();

        while (true) {
            displayChoices();
            System.out.println("Would you like to modify the choices?(y/n)");
            input = MenuUtil.getUserInput();
            input = input.toLowerCase();

            if (input.equals("y")) {
                System.out.println("Which choice would you like to modify(Enter the corresponding letter)?");
                input = MenuUtil.readLetterInAlphabetRange(choices.size());
                input2 = ALPHABET.indexOf(input);
                System.out.print("Enter a new choice: ");
                input = MenuUtil.getUserInput();
                choices.set(input2, input);
            } else if (input.equals("n")) {
                break;
            }
        }

        while (true) {
            System.out.println("This question currently allows for " + numberOfAnswers + " answers/responses.");
            System.out.println("Would you like to change the number of answers/responses?(y/n)");
            input = MenuUtil.getUserInput();
            input = input.toLowerCase();

            if (input.equals("y")) {
                System.out.print("Enter a number(limit is " + choices.size() + "): ");
                input2 = Input.readIntInRange(1, choices.size());
                numberOfAnswers = input2;
            } else if (input.equals("n")) {
                break;
            }
        }
    }

    @Override
    public void take() {
        for (int i = 0; i < numberOfAnswers; i++) {
            String input;

            while (true) {
                input = MenuUtil.readLetterInAlphabetRange(numberOfChoices);

                if (!responseCorrectAnswer.getUserAnswer().contains(input)) {
                    break;
                } else {
                    System.out.println("Choice already selected. Please select another choice.");
                }
            }

            responseCorrectAnswer.addAnswer(input);
        }
    }
}
