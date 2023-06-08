import utils.MenuUtil;

import java.util.ArrayList;

public class TrueFalseQuestion extends MultipleChoiceQuestion {

    private static final long serialVersionUID = 4054418002567287782L;

    public TrueFalseQuestion() {
        choices = new ArrayList<>();
        questionType = "True/False";
        numberOfChoices = 2;
        choices.add("A) True");
        choices.add("B) False");
    }

    @Override
    public void create() {
        prompt = MenuUtil.createQuestionPrompt(questionType);
    }

    @Override
    public void display(int questionNumber) {

        System.out.println((questionNumber + 1) + ") " + prompt + "\n" + String.join("    ", choices) + "\n");
    }

    @Override
    public void modify() {
        modifyPrompt();
    }

    @Override
    public void take() {
        String input;
        input = MenuUtil.readLetterInAlphabetRange(2);
        responseCorrectAnswer.addAnswer(input);
    }
}
