package fa.training.dao.impl;

import fa.training.dao.RoomDAO;
import fa.training.entity.CinemaRoom;

public class RoomDAOImpl extends AbstractGenericDAOImpl<CinemaRoom, Integer> implements RoomDAO {

    public RoomDAOImpl() {
        super(CinemaRoom.class);
    }
}
