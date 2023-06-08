import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        Menu menu = new Menu();

        String[] menu1Options = {"Menu 1", "1) Survey", "2) Test", "3) Quit"};

        String[] surveyMenu2Options = {"Survey Menu 2",
                "1) Create a new Survey",
                "2) Display an existing Survey",
                "3) Load an existing Survey",
                "4) Save the current survey",
                "5) Take the current survey",
                "6) Modify the current survey",
                "7) Tabulate a survey",
                "8) Return to previous menu"};

        String[] testMenu2Options = {"Test Menu 2",
                "1) Create a new Test",
                "2) Display an existing Test without correct answers",
                "3) Display an existing Test with correct answers",
                "4) Load an existing Test",
                "5) Save the current Test",
                "6) Take the current Test",
                "7) Modify the current Test",
                "8) Tabulate a test",
                "9) Grade a Test",
                "10) Return to previous menu"};

        String[] menu3Options = {"Menu 3",
                "1) Add a new T/F question",
                "2) Add a new multiple-choice question",
                "3) Add a new short answer question",
                "4) Add a new essay question",
                "5) Add a new date question",
                "6) Add a new matching question",
                "7) Return to previous menu"};

        menu.setOptions(menu1Options);
        menu.setOptions(surveyMenu2Options);
        menu.setOptions(testMenu2Options);
        menu.setOptions(menu3Options);

        SurveyManager surveyManager = new SurveyManager();
        SurveyManager testManager = new TestManager();
        MenuOptionProcessor processor0 = new MenuOneOptionProcessor(surveyManager);
        MenuOptionProcessor processor1 = new SurveyMenuTwoOptionProcessor(surveyManager);
        MenuOptionProcessor processor2 = new TestMenuTwoOptionProcessor(testManager);
        MenuOptionProcessor processor3 = new MenuThreeOptionProcessor(surveyManager);

        Scanner scanner = new Scanner(System.in);
        while (true) {

            menu.display();
            String selectedOption = scanner.nextLine();


            if (menu.getCurrentPage() == 0) {
                processor0.process(menu, selectedOption);
                if (selectedOption.equals("1")) {
                    processor3.setManager(surveyManager);
                } else if (selectedOption.equals("2")) {
                    processor3.setManager(testManager);
                }
            } else if (menu.getCurrentPage() == 1) {
                processor1.process(menu, selectedOption);
            } else if (menu.getCurrentPage() == 2) {
                processor2.process(menu, selectedOption);
            } else if (menu.getCurrentPage() == 3) {
                processor3.process(menu, selectedOption);
            }

            if (!menu.isActive()) {
                break;
            }
        }
    }
}
