package fa.training;


import fa.training.controller.Controller;
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

public class Main {
    public static void main(String[] args) {
        try{
            SeatDAO seatDAO = new SeatDAOImpl();
            RoomDAO roomDAO = new RoomDAOImpl();
            RoomDetailDAO roomDetailDAO = new RoomDetailDAOImpl();

            SeatService seatService = new SeatServiceImpl(seatDAO);
            CinemaRoomService cinemaRoomService = new CinemaRoomServiceImpl(roomDAO);
            CinemaRoomDetailService cinemaRoomDetailService = new CinemaRoomDetailServiceImpl(roomDetailDAO);

            Controller controller = new Controller(seatService, cinemaRoomService, cinemaRoomDetailService);
            controller.start();
        }finally {
            HibernateUtils.shutdown();
        }
    }
}