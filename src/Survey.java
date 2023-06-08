import utils.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Survey implements Serializable {
    protected static final int TRUE_FALSE = 1;
    protected static final int MULTIPLE_CHOICE = 2;
    protected static final int SHORT_ANSWER = 3;
    protected static final int ESSAY = 4;
    protected static final int DATE = 5;
    protected static final int MATCHING = 6;
    private static final long serialVersionUID = -3080130933191194200L;
    protected String name;
    protected ArrayList<Question> questions;

    public Survey() {
        questions = new ArrayList<>();
        name = "Survey " + (FileUtils.getFileCount(FSConfig.surveyDir) + 1);
    }

    public static Survey load(String path) {
        return (Survey) SerializationHelper.deserialize(Object.class, path);
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void create(int choice) {
        Question question = null;
        if (choice == TRUE_FALSE) {
            question = new TrueFalseQuestion();
            question.create();
        } else if (choice == MULTIPLE_CHOICE) {
            question = new MultipleChoiceQuestion();
            question.create();
        } else if (choice == SHORT_ANSWER) {
            question = new ShortAnswerQuestion();
            question.create();
        } else if (choice == ESSAY) {
            question = new EssayQuestion();
            question.create();
        } else if (choice == DATE) {
            question = new DateQuestion();
            question.create();
        } else if (choice == MATCHING) {
            question = new MatchingQuestion();
            question.create();
        } else {
            System.out.println("Invalid create choice in Survey class.");
        }
        questions.add(question);
    }

    public String getName() {
        return name;
    }

    public void store(String path) {
        SerializationHelper.serialize(Object.class, this, path, name);
    }

    public void display() {
        System.out.println(name);
        int i = 0;
        for (Question question : questions) {
            question.display(i);
            i++;
        }
        MenuUtil.promptReturnToMenu();
    }

    public void modify() {
        int input;

        System.out.println("Which question do you wish to modify?");
        input = Input.readIntInRange(1, questions.size());
        questions.get(input - 1).modify();
    }

    public void take() {
        System.out.println(name);
        int i = 0;
        for (Question question : questions) {
            question.display(i);
            question.take();
            i++;
        }
        System.out.println("Survey Completed.");
        MenuUtil.promptReturnToMenu();
    }

    public void clearAnswer() {
        for (Question question : questions) {
            question.clearAnswer();
        }
    }
}
