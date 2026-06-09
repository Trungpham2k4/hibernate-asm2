package fa.training.view;

public class MainView {
    public void showMainMenu() {
        String menu = """
                
                ===== MOVIE THEATER MANAGEMENT SYSTEM =====
                0. Exit
                1. Seat Management
                2. Cinema Room Management
                3. Cinema Room Details Management
                ===========================================
                Choose one of the following options:
                """;
        System.out.print(menu);
    }

    public void showGoodbye() {
        System.out.println("Goodbye! Have a nice day!");
    }
}