import utils.FileUtils;
import utils.MenuUtil;
import utils.SerializationHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SurveyManager {

    protected Survey loadedSurvey;
    protected QuestionTabulator questionTabulator;

    public SurveyManager() {
        questionTabulator = new QuestionTabulator();
    }

    public void create(Survey survey) {
        loadedSurvey = survey;
    }

    public void createQuestion(int choice) {
        loadedSurvey.create(choice);
    }

    public Survey getLoadedSurvey() {
        return loadedSurvey;
    }

    public void clearLoadedSurvey() {
        loadedSurvey = null;
    }

    public void store(String path) {
        loadedSurvey.store(path);
    }

    public void load(String path) {
        Survey survey = Survey.load(path);
        loadedSurvey = survey;
    }

    public void display() {
        loadedSurvey.display();
    }

    public void modify() {
        loadedSurvey.modify();
    }

    public void take(String responseDir) {
        loadedSurvey.take();
        storeResponseSet(responseDir);
        loadedSurvey.clearAnswer();
    }

    public boolean surveyIsSaved(Survey survey, String surveyDir) {
        File file = new File(surveyDir + survey.getName());
        if (!file.exists() || !file.isFile()) {
            return false;
        }
        return true;
    }

    public void storeResponseSet(String responseDir) {
        ArrayList<ResponseCorrectAnswer> responseSet = new ArrayList<>();
        for (Question question : loadedSurvey.getQuestions()) {
            responseSet.add(question.getAnswer());
        }
        String path = responseDir + loadedSurvey.getName() + " Responses" + File.separator;
        String name = "Response " + (FileUtils.getFileCount(path) + 1);
        SerializationHelper.serialize(Object.class, responseSet, path, name);
    }

    private ArrayList<ResponseCorrectAnswer> loadResponseSet(String path) {
        return (ArrayList<ResponseCorrectAnswer>) SerializationHelper.deserialize(Object.class, path);
    }

    public void tabulate(String surveyPath, String responseDir) {

        Survey survey = Survey.load(surveyPath);

        String responsePath = responseDir + survey.getName() + " Responses" + File.separator;
        File dir = new File(responsePath);

        if (!dir.exists()) {
            System.out.println("There are no responses to tabulate for " + survey.getName() + ".");
            MenuUtil.promptReturnToMenu();
            return;
        }

        ArrayList<ArrayList<ResponseCorrectAnswer>> listOfResponseSets = new ArrayList<>();
        List<String> responseSetPaths = FileUtils.getAllFilePathsInDir(responsePath);

        for (String path : responseSetPaths) {
            listOfResponseSets.add(loadResponseSet(path));
        }

        questionTabulator.tabulate(survey, listOfResponseSets);
    }
}
