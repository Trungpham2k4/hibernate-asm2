package fa.training.view;

import fa.training.entity.CinemaRoom;

import static fa.training.util.InputHandler.getIntInputNullable;
import static fa.training.util.InputHandler.getStringInput;

public class CinemaRoomView extends AbstractView<CinemaRoom> {

    @Override
    public void showMenu() {
        String menu = """
                ===== Cinema Room Management =====
                1. Add Cinema Room
                2. Update Cinema Room
                3. Remove Cinema Room
                4. Show All Cinema Rooms
                5. Show a specific Cinema Room
                """;
        System.out.print(menu);
    }

    @Override
    public CinemaRoom inputForAdd() {
        String roomName = getStringInput("Enter cinema room name: ").trim();
        String cinemaRoomName = roomName.isEmpty() ? null : roomName;
        Integer capacity = getIntInputNullable("Enter cinema room capacity or leave blank for null: ");
        return new CinemaRoom(cinemaRoomName, capacity);
    }

    @Override
    public void inputForUpdate(CinemaRoom existing) {
        String roomName = getStringInput("Enter new cinema room name or leave blank to keep current (" + existing.getCinemaRoomName() + "): ").trim();
        if (!roomName.isEmpty()) {
            existing.setCinemaRoomName(roomName);
        }

        Integer capacity = getIntInputNullable("Enter new capacity or leave blank to keep current (" + existing.getCapacity() + "): ");
        if (capacity != null) {
            existing.setCapacity(capacity);
        }
    }
}
