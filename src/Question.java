import utils.MenuUtil;

import java.io.Serializable;

public abstract class Question implements Serializable {

    protected static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    protected static final int NUMBER_OF_ANSWERS_LIMIT = 26;
    private static final long serialVersionUID = -2463593435216229846L;
    protected String prompt;
    protected String questionType;
    protected int numberOfAnswers;
    protected ResponseCorrectAnswer responseCorrectAnswer;

    public Question() {
        responseCorrectAnswer = new ResponseCorrectAnswer();
    }

    public void displayPrompt() {
        System.out.println(prompt + "\n");
    }

    public String getPrompt() {
        return prompt;
    }

    public abstract void create();

    public abstract void display(int questionNumber);

    public void modifyPrompt() {
        String input;

        while (true) {
            displayPrompt();
            System.out.println("Would you like to modify the prompt?(y/n)");
            input = MenuUtil.getUserInput();
            input = input.toLowerCase();

            if (input.equals("y")) {
                System.out.print("Enter a new prompt: ");
                input = MenuUtil.getUserInput();
                prompt = input;
            } else if (input.equals("n")) {
                break;
            }
        }
    }

    public abstract void modify();

    public abstract void take();

    public ResponseCorrectAnswer getAnswer() {
        return responseCorrectAnswer;
    }

    public void clearAnswer() {
        responseCorrectAnswer.clearAnswer();
    }
}
