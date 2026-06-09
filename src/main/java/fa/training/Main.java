package fa.training;


import fa.training.controller.CinemaRoomController;
import fa.training.controller.CinemaRoomDetailController;
import fa.training.controller.MainController;
import fa.training.controller.SeatController;
import fa.training.dao.RoomDAO;
import fa.training.dao.RoomDetailDAO;
import fa.training.dao.SeatDAO;
import fa.training.dao.impl.RoomDAOImpl;
import fa.training.dao.impl.RoomDetailDAOImpl;
import fa.training.dao.impl.SeatDAOImpl;
import fa.training.service.CinemaRoomDetailService;
import fa.training.service.CinemaRoomService;
import fa.training.service.SeatService;
import fa.training.service.impl.CinemaRoomDetailServiceImpl;
import fa.training.service.impl.CinemaRoomServiceImpl;
import fa.training.service.impl.SeatServiceImpl;
import fa.training.util.HibernateUtils;
import fa.training.view.CinemaRoomDetailView;
import fa.training.view.CinemaRoomView;
import fa.training.view.MainView;
import fa.training.view.SeatView;

public class Main {
    public static void main(String[] args) {
        try{
            SeatDAO seatDAO = new SeatDAOImpl();
            RoomDAO roomDAO = new RoomDAOImpl();
            RoomDetailDAO roomDetailDAO = new RoomDetailDAOImpl();

            SeatService seatService = new SeatServiceImpl(seatDAO);
            CinemaRoomService cinemaRoomService = new CinemaRoomServiceImpl(roomDAO);
            CinemaRoomDetailService cinemaRoomDetailService = new CinemaRoomDetailServiceImpl(roomDetailDAO);

            MainController mainController = getMainController(seatService, cinemaRoomService, cinemaRoomDetailService);
            mainController.start();
        }finally {
            HibernateUtils.shutdown();
        }
    }

    private static MainController getMainController(SeatService seatService, CinemaRoomService cinemaRoomService, CinemaRoomDetailService cinemaRoomDetailService) {
        SeatView seatView = new SeatView();
        CinemaRoomView cinemaRoomView = new CinemaRoomView();
        CinemaRoomDetailView cinemaRoomDetailView = new CinemaRoomDetailView();
        MainView mainView = new MainView();

        SeatController seatController = new SeatController(seatService, cinemaRoomService, seatView);
        CinemaRoomController cinemaRoomController = new CinemaRoomController(cinemaRoomService, cinemaRoomView);
        CinemaRoomDetailController cinemaRoomDetailController = new CinemaRoomDetailController(cinemaRoomService, cinemaRoomDetailService, cinemaRoomDetailView);
        MainController mainController = new MainController(mainView, cinemaRoomController, seatController, cinemaRoomDetailController);
        return mainController;
    }
}