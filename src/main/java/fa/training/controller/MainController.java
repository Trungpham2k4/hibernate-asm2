package fa.training.controller;


import fa.training.view.MainView;

import static fa.training.util.InputHandler.*;

public class MainController{

    private final MainView mainView;
    private final CinemaRoomController cinemaRoomController;
    private final SeatController seatController;
    private final CinemaRoomDetailController cinemaRoomDetailController;

    public MainController (MainView mainView, CinemaRoomController cinemaRoomController, SeatController seatController, CinemaRoomDetailController cinemaRoomDetailController){
        this.mainView = mainView;
        this.cinemaRoomController = cinemaRoomController;
        this.seatController = seatController;
        this.cinemaRoomDetailController = cinemaRoomDetailController;
    }

    public void start(){
        int choice;
        do{
            mainView.showMainMenu();
            choice = inputValidOption(0, 3);
            switch(choice){
                case 1 -> seatController.handleMenu();
                case 2 -> cinemaRoomController.handleMenu();
                case 3 -> cinemaRoomDetailController.handleMenu();
            }
        }while(choice != 0);
        mainView.showGoodbye();
    }
}
