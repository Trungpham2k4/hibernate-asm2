package fa.training.controller;

import fa.training.entity.CinemaRoom;
import fa.training.entity.CinemaRoomDetail;
import fa.training.service.CinemaRoomDetailService;
import fa.training.service.CinemaRoomService;
import fa.training.util.Validator;

import java.time.LocalDate;
import java.util.List;

import static fa.training.util.InputHandler.*;
import static fa.training.util.InputHandler.getIntInputNullable;
import static fa.training.util.InputHandler.getStringInput;
import static fa.training.util.InputHandler.inputValidDate;
import static fa.training.util.InputHandler.inputValidIntField;

public class CinemaRoomDetailController {

    private final CinemaRoomService cinemaRoomService;
    private final CinemaRoomDetailService cinemaRoomDetailService;

    public CinemaRoomDetailController(CinemaRoomService cinemaRoomService, CinemaRoomDetailService cinemaRoomDetailService) {
        this.cinemaRoomService = cinemaRoomService;
        this.cinemaRoomDetailService = cinemaRoomDetailService;
    }

    public void showCinemaRoomDetailsMenu(){
        String menu = """
                ===== Cinema Room Details Management =====
                1. Add Cinema Room Details
                2. Update Cinema Room Details
                3. Remove Cinema Room Details
                4. Show All Cinema Rooms Details
                5. Show a specific Cinema Room Details
                """;
        System.out.print(menu);
    }

    public void handleCinemaRoomDetailsMenu(){
        showCinemaRoomDetailsMenu();
        int choice = inputValidOption(1, 5);
        switch(choice){
            case 1 -> addCinemaRoomDetails();
            case 2 -> updateCinemaRoomDetails();
            case 3 -> deleteCinemaRoomDetails();
            case 4 -> showAllCinemaRoomDetails();
            case 5 -> showCinemaRoomDetailsById();
        }
    }

    public void addCinemaRoomDetails(){
        System.out.println("Adding cinema room details...");
        int cinemaRoomId = inputValidIntField("Enter cinema room ID: ", "ID must be a positive integer", Validator::isPositive);
        CinemaRoom cinemaRoom = cinemaRoomService.findById(cinemaRoomId);
        Integer roomRate = getIntInputNullable("Enter room rate or leave blank for null: ");
        LocalDate activeDate = inputValidDate(Validator::isValidDate);
        String roomDescription = getStringInput("Enter room description: ").trim();
        String cinemaRoomDescription = roomDescription.isEmpty() ? null : roomDescription;
        CinemaRoomDetail cinemaRoomDetail = new CinemaRoomDetail(roomRate, activeDate, cinemaRoomDescription);
        cinemaRoomDetail.setCinemaRoom(cinemaRoom);
        try {
            cinemaRoomDetailService.save(cinemaRoomDetail);
            System.out.println("Cinema room detail added successfully!");
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public void updateCinemaRoomDetails(){
        System.out.println("Updating cinema room details...");
        int cinemaRoomDetailId = inputValidIntField("Enter cinema room detail ID to update: ", "ID must be a positive integer", Validator::isPositive);
        Integer roomRate = getIntInputNullable("Enter room rate to update or leave blank for null: ");
        LocalDate activeDate = inputValidDate(Validator::isValidDate);
        String roomDescription = getStringInput("Enter room description for update: ").trim();
        String cinemaRoomDescription = roomDescription.isEmpty() ? null : roomDescription;
        CinemaRoomDetail cinemaRoomDetail = new CinemaRoomDetail(roomRate, activeDate, cinemaRoomDescription);
        cinemaRoomDetail.setId(cinemaRoomDetailId);
        try {
            cinemaRoomDetailService.update(cinemaRoomDetail);
            System.out.println("Cinema room detail updated successfully!");
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteCinemaRoomDetails(){
        System.out.println("Deleting cinema room details...");
        int cinemaRoomDetailId = inputValidIntField("Enter cinema room detail ID to delete: ", "ID must be a positive integer", Validator::isPositive);
        try {
            cinemaRoomDetailService.delete(cinemaRoomDetailId);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public void showCinemaRoomDetailsById(){
        System.out.println("Showing cinema room details...");
        int cinemaRoomDetailId = inputValidIntField("Enter cinema room detail ID to show: ", "ID must be a positive integer", Validator::isPositive);
        CinemaRoomDetail cinemaRoomDetail = cinemaRoomDetailService.findById(cinemaRoomDetailId);
        if(cinemaRoomDetail != null) {
            System.out.println("Cinema room detail found: " + cinemaRoomDetail);
        }else {
            System.out.println("Cinema room detail with ID " + cinemaRoomDetailId + " not found.");
        }
    }

    public void showAllCinemaRoomDetails(){
        System.out.println("Showing all cinema room details...");
        List<CinemaRoomDetail> cinemaRoomDetails = cinemaRoomDetailService.findAll();
        cinemaRoomDetails.forEach(System.out::println);
    }
}
