public class MenuOneOptionProcessor extends MenuOptionProcessor {

    public MenuOneOptionProcessor(SurveyManager surveyManager) {
        super(surveyManager);
    }

    @Override
    public void process(Menu menu, String selectedOption) {
        if (selectedOption.equals("1")) {
            processOption1(menu);
        } else if (selectedOption.equals("2")) {
            processOption2(menu);
        } else if (selectedOption.equals("3")) {
            processOption3(menu);
        }
    }

    private void processOption1(Menu menu) {
        menu.addPreviousPage(menu.getCurrentPage());
        menu.setCurrentPage(1);
    }

    private void processOption2(Menu menu) {
        menu.addPreviousPage(menu.getCurrentPage());
        menu.setCurrentPage(2);
    }

    private void processOption3(Menu menu) {
        menu.setActive(false);
    }
}
