import java.io.Serializable;
import java.util.ArrayList;

public class ResponseCorrectAnswer implements Serializable {
    private static final long serialVersionUID = 5870404357429037168L;
    private ArrayList<String> userAnswer;

    public ResponseCorrectAnswer() {
        userAnswer = new ArrayList<>();
    }

    public static boolean compare(ResponseCorrectAnswer correctAnswer, ResponseCorrectAnswer answer) {
        ArrayList<String> a = correctAnswer.getUserAnswer();
        ArrayList<String> b = answer.getUserAnswer();
        a.sort(String::compareToIgnoreCase);
        b.sort(String::compareToIgnoreCase);
        boolean answerIsEqual = a.equals(b);
        return answerIsEqual;
    }

    public ArrayList<String> save() {
        return userAnswer;
    }

    public void addAnswer(String input) {
        userAnswer.add(input);
    }

    public ArrayList<String> getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(ArrayList<String> userAnswer) {
        this.userAnswer = userAnswer;
    }

    public void clearAnswer() {
        userAnswer.clear();
    }
}
