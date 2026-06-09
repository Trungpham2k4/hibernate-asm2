package fa.training.controller;


import static fa.training.util.InputHandler.*;

public class Controller {

    public void showMenu(){
        String menu = """
                ===== Movie Theater Menu =====
                0. Exit
                1. Seat Management
                2. Cinema Room Management
                3. Cinema Room Details Management
                """;
        System.out.print(menu);
    }

    public void start(){
        int choice;
        do{
            showMenu();
            choice = inputValidOption(0, 3);
            switch(choice){
                case 1 -> handleSeatMenu();
                case 2 -> handleCinemaRoomMenu();
                case 3 -> handleCinemaRoomDetailsMenu();
            }
        }while(choice != 0);
        System.out.println("Goodbye!");
    }
}
