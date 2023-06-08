import utils.FSConfig;
import utils.FileUtils;
import utils.Input;
import utils.MenuUtil;

import java.util.ArrayList;

public class Test extends Survey {
    private static final long serialVersionUID = -1570645570118871214L;
    private ArrayList<ResponseCorrectAnswer> correctAnswers;

    public Test() {
        correctAnswers = new ArrayList<>();
        name = "Test " + (FileUtils.getFileCount(FSConfig.testDir) + 1);
    }

    public ArrayList<ResponseCorrectAnswer> getCorrectAnswers() {
        return correctAnswers;
    }

    @Override
    public void create(int choice) {
        super.create(choice);
        if (choice == ESSAY) {
            return;
        }
        System.out.println("Enter the correct answer(s) for this question:");
        Question question = questions.get(questions.size() - 1);

        addNewCorrectAnswer(question);
    }

    private void addNewCorrectAnswer(Question question) {
        question.display(questions.size() - 1);
        question.take();
        ResponseCorrectAnswer answer = new ResponseCorrectAnswer();
        answer.setUserAnswer((ArrayList<String>) question.getAnswer().getUserAnswer().clone());
        correctAnswers.add(answer);
        question.clearAnswer();
    }


    public void displayTestWithCorrectAnswers() {
        System.out.println(name);
        int i = 0; //question count
        int j = 0; //correct answer count; skips Essay questions
        for (Question question : questions) {
            if (question.getClass().equals(EssayQuestion.class)) {
                question.display(i);
                System.out.println("This is an essay question. No correct answer(s).");
                i++;
                continue;
            }
            question.display(i);
            String answer = String.join(", ", correctAnswers.get(j).getUserAnswer());
            System.out.println("The correct answer(s) is: " + answer);
            i++;
            j++;
        }
        MenuUtil.promptReturnToMenu();
    }

    @Override
    public void modify() {
        int qNum; //index of question
        Question question;

        System.out.println("Which question do you wish to modify?");
        qNum = Input.readIntInRange(1, questions.size()) - 1;
        question = questions.get(qNum);
        question.modify();

        if (question.getClass().equals(EssayQuestion.class)) {
            return;
        }

        String input;
        while (true) {
            String answer = String.join(", ", correctAnswers.get(qNum).getUserAnswer());
            System.out.println("The correct answer(s) is: " + answer);

            System.out.println("Would you like to modify the correct answer(s)?(y/n)");
            input = MenuUtil.getUserInput();
            input = input.toLowerCase();

            if (input.equals("y")) {
                System.out.println("Enter the new correct answer(s):");
                setCorrectAnswer(question, qNum);

            } else if (input.equals("n")) {
                break;
            }
        }
    }

    private void setCorrectAnswer(Question question, int index) {
        question.display(index);
        question.take();
        ResponseCorrectAnswer answer = new ResponseCorrectAnswer();
        answer.setUserAnswer((ArrayList<String>) question.getAnswer().getUserAnswer().clone());
        correctAnswers.set(index, answer);
        question.clearAnswer();
    }
}
