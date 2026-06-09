package fa.training.service.impl;

import fa.training.dao.RoomDetailDAO;
import fa.training.entity.CinemaRoomDetail;
import fa.training.exception.ResourceNotFoundException;
import fa.training.service.CinemaRoomDetailService;

public class CinemaRoomDetailServiceImpl extends AbstractGenericServiceImpl<CinemaRoomDetail, Integer> implements CinemaRoomDetailService {

    private final RoomDetailDAO roomDetailDAO;

    public CinemaRoomDetailServiceImpl(RoomDetailDAO roomDetailDAO) {
        super(roomDetailDAO);
        this.roomDetailDAO = roomDetailDAO;
    }

    @Override
    public void save(CinemaRoomDetail cinemaRoomDetail) {
        if(cinemaRoomDetail.getCinemaRoom() == null){
            throw new ResourceNotFoundException("Cinema room not found");
        }
        super.save(cinemaRoomDetail);
    }
}
