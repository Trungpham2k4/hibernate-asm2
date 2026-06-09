package fa.training.dao.impl;

import fa.training.dao.SeatDAO;
import fa.training.entity.Seat;

public class SeatDAOImpl extends AbstractGenericDAOImpl<Seat, Integer> implements SeatDAO {
    public SeatDAOImpl() {
        super(Seat.class);
    }
}
