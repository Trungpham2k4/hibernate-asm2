package fa.training.controller;

import fa.training.entity.CinemaRoom;
import fa.training.service.CinemaRoomService;
import fa.training.util.Validator;
import fa.training.view.AbstractView;

import static fa.training.util.InputHandler.*;

public class CinemaRoomController extends AbstractController<CinemaRoom, Integer>{

    public CinemaRoomController(CinemaRoomService cinemaRoomService, AbstractView<CinemaRoom> view) {
        super(cinemaRoomService, view);
    }

    @Override
    protected Integer inputId(String prompt) {
        return inputValidIntField(prompt, "ID must be a positive integer", Validator::isPositive);
    }
}
