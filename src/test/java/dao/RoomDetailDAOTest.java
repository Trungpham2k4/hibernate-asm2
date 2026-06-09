package dao;

import fa.training.dao.RoomDAO;
import fa.training.dao.RoomDetailDAO;
import fa.training.dao.impl.RoomDAOImpl;
import fa.training.dao.impl.RoomDetailDAOImpl;
import fa.training.entity.CinemaRoom;
import fa.training.entity.CinemaRoomDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class RoomDetailDAOTest extends BaseTestDAO {

    private RoomDetailDAO roomDetailDAO;
    private RoomDAO roomDAO;
    private CinemaRoom testRoom;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        roomDetailDAO = new RoomDetailDAOImpl();
        roomDAO = new RoomDAOImpl();

        testRoom = new CinemaRoom("Detail Test Room", 80);
        roomDAO.save(testRoom);
    }

    @Test
    public void testSaveRoomDetail() {
        CinemaRoomDetail detail = new CinemaRoomDetail(50000, LocalDate.now(), "Phòng VIP màn chiếu IMAX");
        detail.setCinemaRoom(testRoom); // Gắn detail vào room

        roomDetailDAO.save(detail);

        assertNotNull(detail.getId());

        CinemaRoomDetail foundDetail = roomDetailDAO.findById(detail.getId());
        assertNotNull(foundDetail);
        assertEquals(50000, foundDetail.getRoomRate());
        assertEquals("Phòng VIP màn chiếu IMAX", foundDetail.getRoomDescription());
        assertEquals(testRoom.getCinemaRoomId(), foundDetail.getCinemaRoom().getCinemaRoomId());
    }

    @Test
    public void testUpdateRoomDetail() {
        CinemaRoomDetail detail = new CinemaRoomDetail(50000, LocalDate.now(), "Old Description");
        detail.setCinemaRoom(testRoom);
        roomDetailDAO.save(detail);

        // Đổi giá và description
        detail.setRoomRate(75000);
        detail.setRoomDescription("New Description");
        roomDetailDAO.update(detail);

        CinemaRoomDetail updatedDetail = roomDetailDAO.findById(detail.getId());
        assertEquals(75000, updatedDetail.getRoomRate());
        assertEquals("New Description", updatedDetail.getRoomDescription());
    }
}