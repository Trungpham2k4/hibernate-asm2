package fa.training.controller;

import fa.training.entity.CinemaRoom;
import fa.training.entity.Seat;
import fa.training.exception.ResourceNotFoundException;
import fa.training.service.CinemaRoomService;
import fa.training.service.SeatService;
import fa.training.util.Validator;
import fa.training.view.AbstractView;

import static fa.training.util.InputHandler.*;

public class SeatController extends AbstractController<Seat, Integer>{

    public final CinemaRoomService cinemaRoomService;

    public SeatController(SeatService seatService, CinemaRoomService cinemaRoomService, AbstractView<Seat> view) {
        super(seatService, view);
        this.cinemaRoomService = cinemaRoomService;
    }

    @Override
    protected Integer inputId(String prompt) {
        return inputValidIntField(prompt, "ID must be a positive integer", Validator::isPositive);
    }

    @Override
    protected void beforeSave(Seat seat) {
        int cinemaRoomId = inputValidIntField("Enter cinema room ID for the seat: ", "ID must be a positive integer", Validator::isPositive);
        CinemaRoom cinemaRoom = cinemaRoomService.findById(cinemaRoomId);
        if (cinemaRoom == null) {
            throw new ResourceNotFoundException("Cinema Room with ID " + cinemaRoomId + " not found");
        }
        seat.setCinemaRoom(cinemaRoom); // Hoàn thiện đối tượng Seat trước khi Save
    }
}
