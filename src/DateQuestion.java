import utils.MenuUtil;

public class DateQuestion extends ShortAnswerQuestion {
    private static final long serialVersionUID = 4680917842737598953L;

    public DateQuestion() {
        wordLimit = 1;
        questionType = "Date";
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
                    "\n" + "(Note: Enter a date in MM/DD/YYYY format)\n");
        } else {
            System.out.println((questionNumber + 1) + ") " + prompt +
                    "\n\t(Note1: Enter a date in MM/DD/YYYY format)" +
                    "\n\t(Note2: Enter each response one by one)" +
                    "\nWrite each of your " + numberOfAnswers + " responses below:\n");
        }
    }

    @Override
    public void take() {
        for (int i = 0; i < numberOfAnswers; i++) {
            String input;
            input = MenuUtil.readDate();
            responseCorrectAnswer.addAnswer(input);
        }
    }

}
