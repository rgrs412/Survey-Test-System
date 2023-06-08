public abstract class MenuOptionProcessor {
    protected SurveyManager surveyManager;

    public MenuOptionProcessor(SurveyManager surveyManager) {
        this.surveyManager = surveyManager;
    }

    public abstract void process(Menu menu, String selectedOption);

    public void setManager(SurveyManager surveyManager) {
        this.surveyManager = surveyManager;
    }
}
