package fa.training.service.impl;

import fa.training.dao.SeatDAO;
import fa.training.entity.Seat;
import fa.training.exception.ResourceNotFoundException;
import fa.training.service.SeatService;

public class SeatServiceImpl extends AbstractGenericServiceImpl<Seat, Integer> implements SeatService {

    public SeatServiceImpl(SeatDAO seatDAO) {
        super(seatDAO);
    }

    @Override
    public void save(Seat seat) {
        if(seat.getCinemaRoom() == null){
            throw new ResourceNotFoundException("Cinema Room not found");
        }
        super.save(seat);
    }

    @Override
    public void update(Seat seat) {
        Seat existing = super.findById(seat.getId());
        if (existing != null) super.update(seat);
        else throw new ResourceNotFoundException("Seat with id " + seat.getId() + " not found");
    }

    @Override
    public void delete(Integer integer) {
        Seat existing = super.findById(integer);
        if (existing != null) super.delete(integer);
        else throw new ResourceNotFoundException("Seat with id " + integer + " not found");
    }
}
