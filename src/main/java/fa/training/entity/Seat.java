package fa.training.entity;

import fa.training.enums.SeatStatus;
import fa.training.enums.SeatType;
import jakarta.persistence.*;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private int seatId;

    @Column(name = "seat_column")
    private String seatColumn;

    @Column(name = "seat_row")
    private Integer seatRow;

    @Column(name = "seat_status")
    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;

    @Column(name = "seat_type")
    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @ManyToOne
    @JoinColumn(name = "cinema_room_id")
    private CinemaRoom cinemaRoom;

    public CinemaRoom getCinemaRoom() {
        return cinemaRoom;
    }

    public void setCinemaRoom(CinemaRoom cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
    }

    public int getId() {
        return seatId;
    }

    public void setId(int id) {
        this.seatId = id;
    }

    public String getSeatColumn() {
        return seatColumn;
    }

    public void setSeatColumn(String seatColumn) {
        this.seatColumn = seatColumn;
    }

    public Integer getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(Integer seatRow) {
        this.seatRow = seatRow;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public Seat(){}
    public Seat(String seatColumn, Integer seatRow, SeatStatus seatStatus, SeatType seatType) {
        this.seatColumn = seatColumn;
        this.seatRow = seatRow;
        this.seatStatus = seatStatus;
        this.seatType = seatType;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatId=" + seatId +
                ", seatColumn='" + seatColumn + '\'' +
                ", seatRow=" + seatRow +
                ", seatStatus=" + seatStatus +
                ", seatType=" + seatType +
                '}';
    }
}
