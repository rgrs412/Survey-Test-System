import java.util.ArrayList;

public class Menu {

    private ArrayList<String[]> options;
    private int currentPage;
    private ArrayList<Integer> previousPage;
    private boolean isActive;

    public Menu() {
        options = new ArrayList<>();
        currentPage = 0;
        previousPage = new ArrayList<>();
        isActive = true;
    }

    public void setOptions(String[] options) {
        this.options.add(options);
    }

    public void display() {
        for (String option : options.get(currentPage)) {
            System.out.println(option);
        }

        System.out.print("\nEnter your choice: ");
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int i) {
        currentPage = i;
    }

    public int popPreviousPage() {
        int page = previousPage.get(previousPage.size() - 1);
        previousPage.remove(previousPage.size() - 1);
        return page;
    }

    public void addPreviousPage(int i) {
        previousPage.add(i);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getPreviousPage() {
        return previousPage.get(previousPage.size() - 1);
    }
}
