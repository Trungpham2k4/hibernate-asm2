package fa.training.controller;

import fa.training.entity.CinemaRoom;
import fa.training.entity.CinemaRoomDetail;
import fa.training.service.CinemaRoomDetailService;
import fa.training.service.CinemaRoomService;
import fa.training.util.Validator;
import fa.training.view.AbstractView;

import static fa.training.util.InputHandler.inputValidIntField;

public class CinemaRoomDetailController extends AbstractController<CinemaRoomDetail, Integer> {

    private final CinemaRoomService cinemaRoomService;

    public CinemaRoomDetailController(CinemaRoomService cinemaRoomService, CinemaRoomDetailService cinemaRoomDetailService, AbstractView<CinemaRoomDetail> view) {
        super(cinemaRoomDetailService, view);
        this.cinemaRoomService = cinemaRoomService;
    }

    @Override
    protected Integer inputId(String prompt) {
        return inputValidIntField(prompt, "ID must be a positive integer", Validator::isPositive);
    }

    protected void beforeSave(CinemaRoomDetail cinemaRoomDetail) {
        int cinemaRoomId = inputValidIntField("Enter cinema room ID for the detail: ", "ID must be a positive integer", Validator::isPositive);
        CinemaRoom cinemaRoom = cinemaRoomService.findById(cinemaRoomId);
        if (cinemaRoom == null) {
            throw new RuntimeException("Cinema Room with ID " + cinemaRoomId + " not found");
        }
        cinemaRoomDetail.setCinemaRoom(cinemaRoom);
    }
}
