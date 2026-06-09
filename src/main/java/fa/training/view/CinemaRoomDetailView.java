package fa.training.view;

import fa.training.entity.CinemaRoomDetail;
import fa.training.util.Validator;

import java.time.LocalDate;

import static fa.training.util.InputHandler.*;

public class CinemaRoomDetailView extends AbstractView<CinemaRoomDetail> {


    @Override
    public void showMenu() {
        String menu = """
                ===== Cinema Room Details Management =====
                1. Add Cinema Room Details
                2. Update Cinema Room Details
                3. Remove Cinema Room Details
                4. Show All Cinema Rooms Details
                5. Show a specific Cinema Room Details
                Choose one of the following options:
                """;
        System.out.print(menu);
    }

    @Override
    public CinemaRoomDetail inputForAdd() {
        Integer roomRate = getIntInputNullable("Enter room rate or leave blank for null: ");
        LocalDate activeDate = inputValidDate(Validator::isValidDate);
        String roomDescription = getStringInput("Enter room description: ").trim();
        String cinemaRoomDescription = roomDescription.isEmpty() ? null : roomDescription;
        return new CinemaRoomDetail(roomRate, activeDate, cinemaRoomDescription);
    }

    @Override
    public void inputForUpdate(CinemaRoomDetail existingEntity) {
        Integer roomRate = getIntInputNullable("Enter new room rate or leave blank to keep current (" + existingEntity.getRoomRate() + "): ");
        if (roomRate != null) {
            existingEntity.setRoomRate(roomRate);
        }

        LocalDate activeDate = inputValidDate(Validator::isValidDate);
        if (activeDate != null) {
            existingEntity.setActiveDate(activeDate);
        }

        String roomDescription = getStringInput("Enter new room description or leave blank to keep current (" + existingEntity.getRoomDescription() + "): ").trim();
        if (!roomDescription.isEmpty()) {
            existingEntity.setRoomDescription(roomDescription);
        }
    }
}
