import utils.FSConfig;
import utils.FileUtils;
import utils.MenuUtil;
import utils.SerializationHelper;

import java.io.File;
import java.util.ArrayList;

public class TestMenuTwoOptionProcessor extends MenuOptionProcessor {

    public TestMenuTwoOptionProcessor(SurveyManager testManager) {
        super(testManager);
    }

    @Override
    public void process(Menu menu, String selectedOption) {
        if (selectedOption.equals("1")) {
            processOption1(menu);
        } else if (selectedOption.equals("2")) {
            processOption2();
        } else if (selectedOption.equals("3")) {
            processOption3();
        } else if (selectedOption.equals("4")) {
            processOption4();
        } else if (selectedOption.equals("5")) {
            processOption5();
        } else if (selectedOption.equals("6")) {
            processOption6();
        } else if (selectedOption.equals("7")) {
            processOption7();
        } else if (selectedOption.equals("8")) {
            processOption8();
        } else if (selectedOption.equals("9")) {
            processOption9();
        } else if (selectedOption.equals("10")) {
            processOption10(menu);
        }
    }

    private void processOption1(Menu menu) {
        menu.addPreviousPage(menu.getCurrentPage());
        menu.setCurrentPage(3);
        surveyManager.create(new Test());
    }

    private void processOption2() {
        Survey loadedSurvey = surveyManager.getLoadedSurvey();
        if (loadedSurvey == null) {
            System.out.println("You must have a test loaded in order to display it.");
            MenuUtil.promptReturnToMenu();
        } else if (loadedSurvey.getQuestions().size() == 0) {
            System.out.println("This test has no questions to display.");
        } else {
            surveyManager.display();
        }
    }

    private void processOption3() {
        Survey loadedSurvey = surveyManager.getLoadedSurvey();
        if (loadedSurvey == null) {
            System.out.println("You must have a test loaded in order to display it.");
            MenuUtil.promptReturnToMenu();
        } else if (loadedSurvey.getQuestions().size() == 0) {
            System.out.println("This test has no questions to display.");
        } else {
            ((TestManager) surveyManager).displayTestWithCorrectAnswers();
        }
    }

    private void processOption4() {
        try {
            String path = FileUtils.listAndPickFileFromDir(FSConfig.testDir);
            surveyManager.load(path);
        } catch (IllegalStateException e) {
            System.out.println("No tests to load. Create and save a test to load it.");
            MenuUtil.promptReturnToMenu();
        }
    }

    private void processOption5() {
        Survey loadedSurvey = surveyManager.getLoadedSurvey();
        if (loadedSurvey == null) {
            System.out.println("You must have a test loaded in order to save it.");
            MenuUtil.promptReturnToMenu();
        } else if (loadedSurvey.getQuestions().size() == 0) {
            System.out.println("This test has no questions to save.");
        } else {
            surveyManager.store(FSConfig.testDir);
        }
    }

    private void processOption6() {
        Survey loadedSurvey = surveyManager.getLoadedSurvey();
        if (loadedSurvey == null) {
            System.out.println("You must have a test loaded in order to take it.");
            MenuUtil.promptReturnToMenu();
        } else if (!surveyManager.surveyIsSaved(loadedSurvey, FSConfig.testDir)) {
            System.out.println("You must save the loaded test in order to take it.");
            MenuUtil.promptReturnToMenu();
        } else if (loadedSurvey.getQuestions().size() == 0) {
            System.out.println("This test has no questions to fill out.");
        } else {
            surveyManager.take(FSConfig.testResponseDir);
        }
    }

    private void processOption7() {
        Survey loadedSurvey = surveyManager.getLoadedSurvey();
        if (loadedSurvey == null) {
            System.out.println("You must have a test loaded in order to modify it.");
            MenuUtil.promptReturnToMenu();
        } else if (loadedSurvey.getQuestions().size() == 0) {
            System.out.println("This test has no questions to modify.");
        } else {
            surveyManager.modify();
        }
    }

    private void processOption8() {
        try {
            String path = FileUtils.listAndPickFileFromDir(FSConfig.testDir);
            surveyManager.tabulate(path, FSConfig.testResponseDir);
        } catch (IllegalStateException e) {
            System.out.println("No tests to tabulate. Create and save a test to tabulate it.");
            MenuUtil.promptReturnToMenu();
        }
    }

    private void processOption9() {
        String testPath;
        String testResponseDirPath;
        String testResponsePath;
        Survey test;
        ArrayList<ResponseCorrectAnswer> responseSet;

        testPath = MenuUtil.getTestPath();
        if (testPath != null) {
            test = SerializationHelper.deserialize(Survey.class, testPath);
            testResponseDirPath = FSConfig.testResponseDir + test.getName() + " Responses" + File.separator;

            testResponsePath = MenuUtil.getTestResponsePath(testResponseDirPath);
            if (testResponsePath != null) {
                responseSet = (ArrayList<ResponseCorrectAnswer>) SerializationHelper.deserialize(Object.class, testResponsePath);
                ((TestManager) surveyManager).grade(test, responseSet);
            }
        }
    }

    private void processOption10(Menu menu) {
        menu.setCurrentPage(menu.popPreviousPage());
    }
}
