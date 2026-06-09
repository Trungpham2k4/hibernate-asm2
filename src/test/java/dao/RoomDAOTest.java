package dao;

import fa.training.dao.RoomDAO;
import fa.training.dao.impl.RoomDAOImpl;
import fa.training.entity.CinemaRoom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoomDAOTest extends BaseTestDAO {

    private RoomDAO roomDAO;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp(); // Bắt buộc gọi để mở Transaction
        roomDAO = new RoomDAOImpl(); // Khởi tạo DAO
    }

    @Test
    public void testSaveAndFindById() {
        // 1. Chuẩn bị (Tạo data)
        CinemaRoom room = new CinemaRoom("Room VIP 1", 50);

        // 2. Thực thi
        roomDAO.save(room);

        // 3. Kiểm chứng
        assertNotNull(room.getCinemaRoomId(), "ID phải được generate sau khi save");

        CinemaRoom foundRoom = roomDAO.findById(room.getCinemaRoomId());
        assertNotNull(foundRoom);
        assertEquals("Room VIP 1", foundRoom.getCinemaRoomName());
        assertEquals(50, foundRoom.getCapacity());
    }

    @Test
    public void testUpdate() {
        CinemaRoom room = new CinemaRoom("Room To Update", 30);
        roomDAO.save(room); // Lưu trước

        // Đổi tên và update
        room.setCinemaRoomName("Room Updated");
        roomDAO.update(room);

        // Kiểm tra lại
        CinemaRoom updatedRoom = roomDAO.findById(room.getCinemaRoomId());
        assertEquals("Room Updated", updatedRoom.getCinemaRoomName());
    }

    @Test
    public void testFindAll() {
        roomDAO.save(new CinemaRoom("Room A", 40));
        roomDAO.save(new CinemaRoom("Room B", 60));

        List<CinemaRoom> rooms = roomDAO.findAll();

        // Phải lớn hơn hoặc bằng 2 vì có thể trong DB thật của bạn đã có sẵn data từ trước
        assertTrue(rooms.size() >= 2, "Phải lấy ra được ít nhất 2 phòng chiếu");
    }

    @Test
    public void testDelete() {
        CinemaRoom room = new CinemaRoom("Room To Delete", 20);
        roomDAO.save(room);
        int savedId = room.getCinemaRoomId();

        // Thực hiện xóa
        roomDAO.delete(savedId);

        // Kiểm tra lại xem còn không
        CinemaRoom foundRoom = roomDAO.findById(savedId);
        assertNull(foundRoom, "Phòng chiếu phải bằng null sau khi xóa");
    }
}