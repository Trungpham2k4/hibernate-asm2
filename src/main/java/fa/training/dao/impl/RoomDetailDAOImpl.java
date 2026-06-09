package fa.training.dao.impl;

import fa.training.dao.RoomDetailDAO;
import fa.training.entity.CinemaRoomDetail;

public class RoomDetailDAOImpl extends AbstractGenericDAOImpl<CinemaRoomDetail, Integer> implements RoomDetailDAO {

    public RoomDetailDAOImpl() {
        super(CinemaRoomDetail.class);
    }
}
