import utils.Input;
import utils.MenuUtil;

public class EssayQuestion extends Question {
    private static final long serialVersionUID = 7505148087815671324L;
    protected int wordLimit;
    //protected ArrayList<String> answer;

    public EssayQuestion() {
        wordLimit = 150;
        questionType = "Essay";
    }

    @Override
    public void create() {
        numberOfAnswers = MenuUtil.promptNumberOfAnswers(questionType, NUMBER_OF_ANSWERS_LIMIT);
        prompt = MenuUtil.createQuestionPrompt(questionType);
    }

    @Override
    public void display(int questionNumber) {
        if (numberOfAnswers == 1) {
            System.out.println((questionNumber + 1) + ") " + prompt +
                    "\nWrite your response below (" + wordLimit + " word limit): \n");
        } else {
            System.out.println((questionNumber + 1) + ") " + prompt +
                    "\n\t(Note1: " + wordLimit + " word limit per response)" +
                    "\n\t(Note2: Enter each response one by one)" +
                    "\nWrite each of your " + numberOfAnswers + " responses below:\n");
        }
    }

    @Override
    public void modify() {
        String input;
        int input2;

        modifyPrompt();

        while (true) {
            System.out.println("This question currently allows for " + numberOfAnswers + " answers/responses.");
            System.out.println("Would you like to change the number of answers/responses?(y/n)");
            input = MenuUtil.getUserInput();
            input = input.toLowerCase();

            if (input.equals("y")) {
                System.out.print("Enter a number(limit is " + NUMBER_OF_ANSWERS_LIMIT + "): ");
                input2 = Input.readIntInRange(1, NUMBER_OF_ANSWERS_LIMIT);
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
                input = MenuUtil.getUserInput();
                int wordLength = input.split("\\s+").length;
                if (wordLength > wordLimit) {
                    System.out.println("Word limit exceeded. Try again.");
                    continue;
                }
                break;
            }
            responseCorrectAnswer.addAnswer(input);
        }
    }
}
