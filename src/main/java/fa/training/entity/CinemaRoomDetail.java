package fa.training.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class CinemaRoomDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cinema_room_detail_id")
    private int id;

    @Column(name = "room_rate")
    private Integer roomRate;

    @Column(name = "active_date")
    private LocalDate activeDate;

    @Column(name = "room_description")
    private String roomDescription;

    @OneToOne
    @JoinColumn(name = "cinema_room_id")
    private CinemaRoom cinemaRoom;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CinemaRoom getCinemaRoom() {
        return cinemaRoom;
    }

    public void setCinemaRoom(CinemaRoom cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
    }

    public Integer getRoomRate() {
        return roomRate;
    }

    public void setRoomRate(Integer roomRate) {
        this.roomRate = roomRate;
    }

    public LocalDate getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(LocalDate activeDate) {
        this.activeDate = activeDate;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }


    public CinemaRoomDetail() {}

    public CinemaRoomDetail(Integer roomRate, LocalDate activeDate, String roomDescription) {
        this.roomRate = roomRate;
        this.activeDate = activeDate;
        this.roomDescription = roomDescription;
    }

    @Override
    public String toString() {
        return "CinemaRoomDetail{" +
                "id=" + id +
                ", roomRate=" + roomRate +
                ", activeDate=" + activeDate +
                ", roomDescription='" + roomDescription + '\'' +
                '}';
    }
}
