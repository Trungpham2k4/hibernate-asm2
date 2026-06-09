package fa.training.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class CinemaRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cinema_room_id")
    private int cinemaRoomId;

    @Column(name = "cinema_room_name", unique = true)
    private String cinemaRoomName;

    @Column(name = "seat_quantity")
    private Integer capacity;

    @OneToMany(mappedBy = "cinemaRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Seat> seats;

    @OneToOne(mappedBy = "cinemaRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private CinemaRoomDetail cinemaRoomDetail;

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    public int getCinemaRoomId() {
        return cinemaRoomId;
    }

    public void setCinemaRoomId(int cinemaRoomId) {
        this.cinemaRoomId = cinemaRoomId;
    }

    public String getCinemaRoomName() {
        return cinemaRoomName;
    }

    public void setCinemaRoomName(String cinemaRoomName) {
        this.cinemaRoomName = cinemaRoomName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public CinemaRoom() {}

    public CinemaRoom(String cinemaRoomName, Integer capacity) {
        this.cinemaRoomName = cinemaRoomName;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "CinemaRoom{" +
                "cinemaRoomId=" + cinemaRoomId +
                ", cinemaRoomName='" + cinemaRoomName + '\'' +
                ", capacity=" + capacity +
                ", cinemaRoomDetail=" + cinemaRoomDetail +
                '}';
    }
}
