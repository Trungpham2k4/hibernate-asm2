package fa.training.controller;

import fa.training.entity.CinemaRoom;
import fa.training.entity.Seat;
import fa.training.enums.SeatStatus;
import fa.training.enums.SeatType;
import fa.training.service.CinemaRoomService;
import fa.training.service.SeatService;
import fa.training.util.Validator;

import java.util.List;
import static fa.training.util.InputHandler.*;

public class SeatController {

    public final SeatService seatService;
    public final CinemaRoomService cinemaRoomService;

    public SeatController(SeatService seatService, CinemaRoomService cinemaRoomService) {
        this.seatService = seatService;
        this.cinemaRoomService = cinemaRoomService;
    }

    public void showSeatMenu(){
        String menu = """
                ===== Seat Management =====
                1. Add Seat
                2. Update Seat
                3. Remove Seat
                4. Show All Seats
                5. Show a specific Seat
                """;
        System.out.print(menu);
    }

    public void handleSeatMenu(){
        showSeatMenu();
        int choice = inputValidOption(1, 5);
        switch(choice){
            case 1 -> addSeat();
            case 2 -> updateSeat();
            case 3 -> deleteSeat();
            case 4 -> findAllSeats();
            case 5 -> findSeatById();
        }
    }

    public void addSeat(){
        System.out.println("Adding a new seat...");
        int cinemaRoomId = inputValidIntField("Enter cinema room ID for the seat: ", "ID must be a positive integer", Validator::isPositive);
        CinemaRoom cinemaRoom = cinemaRoomService.findById(cinemaRoomId);
        String column = getStringInput("Enter seat column (e.g., A, B, C): ").trim();
        String seatColumn = column.isEmpty() ? null : column;
        Integer seatRow = getIntInputNullable("Enter seat row (e.g., 1, 2, 3) or leave blank for null: ");
        SeatStatus seatStatus = inputValidSeatStatus(Validator::isValidSeatStatus);
        SeatType seatType = inputValidSeatType(Validator::isValidSeatType);

        Seat seat = new Seat(seatColumn, seatRow, seatStatus, seatType);
        seat.setCinemaRoom(cinemaRoom);
        try{
            seatService.save(seat);
            System.out.println("Seat added successfully!");
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public void updateSeat(){
        System.out.println("Updating seat...");
        int id = inputValidIntField("Enter employee ID to update: ", "ID must be a positive integer", Validator::isPositive);
        String column = getStringInput("Enter new seat column (e.g., A, B, C) or leave blank to keep current: ").trim();
        String seatColumn = column.isEmpty() ? null : column;
        Integer seatRow = getIntInputNullable("Enter new seat row (e.g., 1, 2, 3) or leave blank to keep current: ");
        SeatStatus seatStatus = inputValidSeatStatus(Validator::isValidSeatStatus);
        SeatType seatType = inputValidSeatType(Validator::isValidSeatType);
        Seat seat  = new Seat(seatColumn, seatRow, seatStatus, seatType);
        seat.setId(id);
        try {
            seatService.update(seat);
            System.out.println("Seat updated successfully!");
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
    public void deleteSeat(){
        System.out.println("Deleting seat...");
        int id = inputValidIntField("Enter seat ID to delete: ", "ID must be a positive integer", Validator::isPositive);
        try {
            seatService.delete(id);
            System.out.println("Seat deleted successfully!");
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public void findSeatById(){
        System.out.println("Finding seat by ID...");
        int id = inputValidIntField("Enter seat ID to find: ", "ID must be a positive integer", Validator::isPositive);
        Seat seat = seatService.findById(id);
        if(seat != null){
            System.out.println("Seat found: " + seat);
        }else {
            System.out.println("Seat with ID " + id + " not found.");
        }
    }

    public void findAllSeats(){
        System.out.println("Finding all seats...");
        List<Seat> employees = seatService.findAll();
        employees.forEach(System.out::println);
    }
}
