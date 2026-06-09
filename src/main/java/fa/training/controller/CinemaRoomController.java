package fa.training.controller;

import fa.training.entity.CinemaRoom;
import fa.training.service.CinemaRoomService;
import fa.training.util.Validator;

import java.util.List;

import static fa.training.util.InputHandler.*;

public class CinemaRoomController {

    private final CinemaRoomService cinemaRoomService;

    public CinemaRoomController(CinemaRoomService cinemaRoomService) {
        this.cinemaRoomService = cinemaRoomService;
    }

    public void showCinemaRoomMenu(){
        String menu = """
                ===== Cinema Room Management =====
                1. Add Cinema Room
                2. Update Cinema Room
                3. Remove Cinema Room
                4. Show All Cinema Rooms
                5. Show a specific Cinema Room
                """;
        System.out.print(menu);
    }

    public void handleCinemaRoomMenu(){
        showCinemaRoomMenu();
        int choice = inputValidOption(1, 5);
        switch(choice){
            case 1 -> addCinemaRoom();
            case 2 -> updateCinemaRoom();
            case 3 -> deleteCinemaRoom();
            case 4 -> findAllCinemaRooms();
            case 5 -> findCinemaRoomById();
        }
    }

    public void addCinemaRoom(){
        System.out.println("Adding a cinema room...");
        String roomName = getStringInput("Enter cinema room name: ").trim();
        String cinemaRoomName = roomName.isEmpty() ? null : roomName;
        Integer capacity = getIntInputNullable("Enter cinema room capacity or leave blank for null: ");
        CinemaRoom cinemaRoom = new CinemaRoom(cinemaRoomName, capacity);
        try {
            cinemaRoomService.save(cinemaRoom);
            System.out.println("Cinema room added successfully!");
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public void updateCinemaRoom(){
        System.out.println("Updating cinema room...");
        int id = inputValidIntField("Enter cinema room ID to update: ", "ID must be a positive integer", Validator::isPositive);
        String roomName = getStringInput("Enter new cinema room name or leave blank to keep current: ").trim();
        String cinemaRoomName = roomName.isEmpty() ? null : roomName;
        Integer capacity = getIntInputNullable("Enter new cinema room capacity or leave blank to keep current: ");
        CinemaRoom cinemaRoom = new CinemaRoom(cinemaRoomName, capacity);
        cinemaRoom.setCinemaRoomId(id);
        try {
            cinemaRoomService.update(cinemaRoom);
            System.out.println("Cinema room updated successfully!");
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteCinemaRoom(){
        System.out.println("Deleting cinema room...");
        int id = inputValidIntField("Enter cinema room ID to delete: ", "ID must be a positive integer", Validator::isPositive);
        try {
            cinemaRoomService.delete(id);
            System.out.println("Cinema room deleted successfully!");
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public void findCinemaRoomById(){
        System.out.println("Finding cinema room by ID...");
        int id = inputValidIntField("Enter cinema room ID to find: ", "ID must be a positive integer", Validator::isPositive);
        CinemaRoom cinemaRoom = cinemaRoomService.findById(id);
        if(cinemaRoom != null){
            System.out.println("Cinema room found: " + cinemaRoom);
        }else {
            System.out.println("Cinema room with ID " + id + " not found.");
        }
    }

    public void findAllCinemaRooms(){
        System.out.println("Finding all cinema rooms...");
        List<CinemaRoom> cinemaRooms = cinemaRoomService.findAll();
        cinemaRooms.forEach(System.out::println);
    }
}
