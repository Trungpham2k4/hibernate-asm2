package dao;

import fa.training.dao.RoomDAO;
import fa.training.dao.SeatDAO;
import fa.training.dao.impl.RoomDAOImpl;
import fa.training.dao.impl.SeatDAOImpl;
import fa.training.entity.CinemaRoom;
import fa.training.entity.Seat;
import fa.training.enums.SeatStatus;
import fa.training.enums.SeatType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SeatDAOTest extends BaseTestDAO {

    private SeatDAO seatDAO;
    private RoomDAO roomDAO;
    private CinemaRoom testRoom;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        seatDAO = new SeatDAOImpl();
        roomDAO = new RoomDAOImpl();

        // Tạo data mẫu: Luôn cần 1 phòng chiếu để gán ghế vào
        testRoom = new CinemaRoom("Seat Test Room", 100);
        roomDAO.save(testRoom);
    }

    @Test
    public void testSaveSeatWithCinemaRoom() {
        Seat seat = new Seat("A", 1, SeatStatus.AVAILABLE, SeatType.NORMAL);
        seat.setCinemaRoom(testRoom); // Map khóa ngoại

        seatDAO.save(seat);

        assertNotNull(seat.getId());

        Seat foundSeat = seatDAO.findById(seat.getId());
        assertNotNull(foundSeat);
        assertEquals("A", foundSeat.getSeatColumn());
        assertEquals(SeatType.NORMAL, foundSeat.getSeatType());

        // Kiểm tra quan hệ khóa ngoại
        assertNotNull(foundSeat.getCinemaRoom());
        assertEquals("Seat Test Room", foundSeat.getCinemaRoom().getCinemaRoomName());
    }

    @Test
    public void testDeleteSeat() {
        Seat seat = new Seat("B", 2, SeatStatus.BOOKED, SeatType.VIP);
        seat.setCinemaRoom(testRoom);
        seatDAO.save(seat);

        int seatId = seat.getId();
        seatDAO.delete(seatId);

        assertNull(seatDAO.findById(seatId));
    }
}