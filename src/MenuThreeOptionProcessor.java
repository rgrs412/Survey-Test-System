public class MenuThreeOptionProcessor extends MenuOptionProcessor {

    public MenuThreeOptionProcessor(SurveyManager surveyManager) {
        super(surveyManager);
    }

    @Override
    public void process(Menu menu, String selectedOption) {
        if (selectedOption.equals("1")) {
            processOption1();
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
            processOption7(menu);
        }
    }

    private void processOption1() {
        surveyManager.createQuestion(1);
    }

    private void processOption2() {
        surveyManager.createQuestion(2);
    }

    private void processOption3() {
        surveyManager.createQuestion(3);
    }

    private void processOption4() {
        surveyManager.createQuestion(4);
    }

    private void processOption5() {
        surveyManager.createQuestion(5);
    }

    private void processOption6() {
        surveyManager.createQuestion(6);
    }

    private void processOption7(Menu menu) {
        if (surveyManager.getLoadedSurvey().getQuestions().size() == 0) {
            surveyManager.clearLoadedSurvey();
        }
        menu.setCurrentPage(menu.popPreviousPage());
    }

}
