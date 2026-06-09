package fa.training.service.impl;

import fa.training.dao.RoomDAO;
import fa.training.entity.CinemaRoom;
import fa.training.service.CinemaRoomService;

public class CinemaRoomServiceImpl extends AbstractGenericServiceImpl<CinemaRoom, Integer> implements CinemaRoomService {
    public CinemaRoomServiceImpl(RoomDAO roomDAO) {
        super(roomDAO);
    }
}
