import utils.MenuUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestionTabulator {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String tabulation;

    public QuestionTabulator() {
        tabulation = "";
    }

    public void tabulate(Survey survey, ArrayList<ArrayList<ResponseCorrectAnswer>> listOfResponseSets) {
        int index = 0;
        for (Question question : survey.getQuestions()) {
            if (question.getClass().equals(TrueFalseQuestion.class)) {
                System.out.println(tabulateTrueFalse(question, index, listOfResponseSets));
            } else if (question.getClass().equals(MultipleChoiceQuestion.class)) {
                System.out.println(tabulateMultipleChoice(question, index, listOfResponseSets));
            } else if (question.getClass().equals(ShortAnswerQuestion.class)) {
                System.out.println(tabulateShortAnswer(question, index, listOfResponseSets));
            } else if (question.getClass().equals(EssayQuestion.class)) {
                System.out.println(tabulateEssay(question, index, listOfResponseSets));
            } else if (question.getClass().equals(DateQuestion.class)) {
                //Date uses ShortAnswer tabulate method
                System.out.println(tabulateShortAnswer(question, index, listOfResponseSets));
            } else if (question.getClass().equals(MatchingQuestion.class)) {
                System.out.println(tabulateMatching(question, index, listOfResponseSets));
            }
            index++;
        }
        MenuUtil.promptReturnToMenu();
    }

    private String tabulateMatching(Question question, int index,
                                    ArrayList<ArrayList<ResponseCorrectAnswer>> listOfResponseSets) {
        clearTabulation();
        HashMap<String, Integer> answerCount = new HashMap<>();

        tabulation = (index + 1) + ") " + question.getPrompt() + "\n";

        for (ArrayList<ResponseCorrectAnswer> responseSet : listOfResponseSets) {
            String answer = String.join("\n", responseSet.get(index).getUserAnswer());

            if (answerCount.containsKey(answer)) {
                answerCount.put(answer, answerCount.get(answer) + 1);
            } else {
                answerCount.put(answer, 1);
            }
        }

        answerCount.forEach((k, v) -> tabulation += "\n" + v + "\n" + k + "\n");

        return tabulation;
    }

    private String tabulateEssay(Question question, int index,
                                 ArrayList<ArrayList<ResponseCorrectAnswer>> listOfResponseSets) {
        clearTabulation();
        tabulation = (index + 1) + ") " + question.getPrompt() + "\n";

        for (ArrayList<ResponseCorrectAnswer> responseSet : listOfResponseSets) {
            for (String answer : responseSet.get(index).getUserAnswer()) {
                tabulation += "\n" + answer + "\n";
            }
        }
        return tabulation;
    }

    private String tabulateShortAnswer(Question question, int index,
                                       ArrayList<ArrayList<ResponseCorrectAnswer>> listOfResponseSets) {
        clearTabulation();
        HashMap<String, Integer> answerCount = new HashMap<>();

        tabulation = (index + 1) + ") " + question.getPrompt() + "\n";

        for (ArrayList<ResponseCorrectAnswer> responseSet : listOfResponseSets) {
            for (String answer : responseSet.get(index).getUserAnswer()) {
                if (answerCount.containsKey(answer)) {
                    answerCount.put(answer, answerCount.get(answer) + 1);
                } else {
                    answerCount.put(answer, 1);
                }
            }
        }

        answerCount.forEach((k, v) -> tabulation += k + ": " + v + "\n");

        return tabulation;
    }

    private String tabulateMultipleChoice(Question question, int index,
                                          ArrayList<ArrayList<ResponseCorrectAnswer>> listOfResponseSets) {
        clearTabulation();
        int numberOfChoices = ((MultipleChoiceQuestion) question).getNumberOfChoices();
        int[] choiceCount = new int[numberOfChoices];

        tabulation = (index + 1) + ") " + question.getPrompt() + "\n";

        for (ArrayList<ResponseCorrectAnswer> responseSet : listOfResponseSets) {
            for (String answer : responseSet.get(index).getUserAnswer()) {
                if (ALPHABET.indexOf(answer) > -1) {
                    choiceCount[ALPHABET.indexOf(answer)] += 1;
                }
            }
        }

        for (int i = 0; i < numberOfChoices; i++) {
            tabulation += ALPHABET.charAt(i) + ": " + choiceCount[i] + "\n";
        }

        return tabulation;
    }

    private String tabulateTrueFalse(Question question, int index,
                                     ArrayList<ArrayList<ResponseCorrectAnswer>> listOfResponseSets) {
        clearTabulation();
        int trueCount = 0;
        int falseCount = 0;

        for (ArrayList<ResponseCorrectAnswer> responseSet : listOfResponseSets) {
            String answer = responseSet.get(index).getUserAnswer().get(0);
            if (answer.equals("A")) {
                trueCount += 1;
            } else if (answer.equals("B")) {
                falseCount += 1;
            }
        }

        tabulation = String.format("%d) %s\nTrue: %d\nFalse: %d\n", (index + 1), question.getPrompt(), trueCount, falseCount);

        return tabulation;
    }

    private void clearTabulation() {
        tabulation = "";
    }
}
