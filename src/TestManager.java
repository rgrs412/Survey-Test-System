import utils.MenuUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TestManager extends SurveyManager {
    private static final double MAX_SCORE = 100;

    public void displayTestWithCorrectAnswers() {
        ((Test) loadedSurvey).displayTestWithCorrectAnswers();
    }

    public void grade(Survey test, ArrayList<ResponseCorrectAnswer> responseSet) {
        int i = 0; //question count
        int j = 0; //correct answer count; skips Essay questions
        double totalScore = 0;
        double questionWeight = MAX_SCORE / test.getQuestions().size();
        boolean answerIsCorrect;
        ArrayList<ResponseCorrectAnswer> correctAnswers = ((Test) test).getCorrectAnswers();

        for (ResponseCorrectAnswer answer : responseSet) {
            if (test.getQuestions().get(i).getClass().equals(EssayQuestion.class)) {
                i++;
                continue;
            }

            answerIsCorrect = ResponseCorrectAnswer.compare(correctAnswers.get(j), answer);
            if (answerIsCorrect) {
                totalScore += questionWeight;
            }
            j++;
            i++;
        }

        DecimalFormat df = new DecimalFormat("##.00");

        if (j == i) {
            System.out.println("Grade: " + df.format(totalScore) + "/" + df.format(MAX_SCORE));
        } else {
            double autogradableScore = j * questionWeight;
            int numOfEssayQuestion = test.getQuestions().size() - j;
            System.out.println("Grade: " + df.format(totalScore) + "/" + df.format(MAX_SCORE) + "\n");

            System.out.println("You received an " + df.format(totalScore) + " on the test. " +
                    "The test was worth 100.00 points, but only " + df.format(autogradableScore) + " of\n" +
                    "those points could be auto graded because there was " + numOfEssayQuestion +
                    " essay question(s) out of " + i + " total question(s).");
        }
        MenuUtil.promptReturnToMenu();
    }
}
