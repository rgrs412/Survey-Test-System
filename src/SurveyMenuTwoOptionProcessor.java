import utils.FSConfig;
import utils.FileUtils;
import utils.MenuUtil;

public class SurveyMenuTwoOptionProcessor extends MenuOptionProcessor {

    public SurveyMenuTwoOptionProcessor(SurveyManager surveyManager) {
        super(surveyManager);
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
            processOption8(menu);
        }
    }

    private void processOption1(Menu menu) {
        menu.addPreviousPage(menu.getCurrentPage());
        menu.setCurrentPage(3);
        surveyManager.create(new Survey());
    }

    private void processOption2() {
        Survey loadedSurvey = surveyManager.getLoadedSurvey();
        if (loadedSurvey == null) {
            System.out.println("You must have a survey loaded in order to display it.");
            MenuUtil.promptReturnToMenu();
        } else if (loadedSurvey.getQuestions().size() == 0) {
            System.out.println("This survey has no questions to display.");
        } else {
            surveyManager.display();
        }
    }

    private void processOption3() {
        try {
            String path = FileUtils.listAndPickFileFromDir(FSConfig.surveyDir);
            surveyManager.load(path);
        } catch (IllegalStateException e) {
            System.out.println("No surveys to load. Create and save a survey to load it.");
            MenuUtil.promptReturnToMenu();
        }
    }

    private void processOption4() {
        Survey loadedSurvey = surveyManager.getLoadedSurvey();
        if (loadedSurvey == null) {
            System.out.println("You must have a survey loaded in order to save it.");
            MenuUtil.promptReturnToMenu();
        } else if (loadedSurvey.getQuestions().size() == 0) {
            System.out.println("This survey has no questions to save.");
        } else {
            surveyManager.store(FSConfig.surveyDir);
        }
    }

    private void processOption5() {
        Survey loadedSurvey = surveyManager.getLoadedSurvey();
        if (loadedSurvey == null) {
            System.out.println("You must have a survey loaded in order to take it.");
            MenuUtil.promptReturnToMenu();
        } else if (!surveyManager.surveyIsSaved(loadedSurvey, FSConfig.surveyDir)) {
            System.out.println("You must save the loaded survey in order to take it.");
            MenuUtil.promptReturnToMenu();
        } else if (loadedSurvey.getQuestions().size() == 0) {
            System.out.println("This survey has no questions to fill out.");
        } else {
            surveyManager.take(FSConfig.surveyResponseDir);
        }
    }

    private void processOption6() {
        Survey loadedSurvey = surveyManager.getLoadedSurvey();
        if (loadedSurvey == null) {
            System.out.println("You must have a survey loaded in order to modify it.");
            MenuUtil.promptReturnToMenu();
        } else if (loadedSurvey.getQuestions().size() == 0) {
            System.out.println("This survey has no questions to modify.");
        } else {
            surveyManager.modify();
        }
    }

    private void processOption7() {
        try {
            String path = FileUtils.listAndPickFileFromDir(FSConfig.surveyDir);
            surveyManager.tabulate(path, FSConfig.surveyResponseDir);
        } catch (IllegalStateException e) {
            System.out.println("No surveys to tabulate. Create and save a survey to tabulate it.");
            MenuUtil.promptReturnToMenu();
        }
    }

    private void processOption8(Menu menu) {
        menu.setCurrentPage(menu.popPreviousPage());
    }

}
