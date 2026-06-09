package fa.training.view;

import fa.training.entity.Seat;
import fa.training.enums.SeatStatus;
import fa.training.enums.SeatType;
import fa.training.util.Validator;

import static fa.training.util.InputHandler.*;

public class SeatView extends AbstractView<Seat>{
    @Override
    public void showMenu() {
        String menu = """
                ===== Seat Management =====
                1. Add Seat
                2. Update Seat
                3. Remove Seat
                4. Show All Seats
                5. Show a specific Seat
                """;
        System.out.print(menu);
    }

    @Override
    public Seat inputForAdd() {
        String column = getStringInput("Enter seat column (e.g., A, B, C): ").trim();
        String seatColumn = column.isEmpty() ? null : column;
        Integer seatRow = getIntInputNullable("Enter seat row (e.g., 1, 2, 3) or leave blank for null: ");
        SeatStatus seatStatus = inputValidSeatStatus(Validator::isValidSeatStatus);
        SeatType seatType = inputValidSeatType(Validator::isValidSeatType);
        return new Seat(seatColumn, seatRow, seatStatus, seatType);
    }

    @Override
    public void inputForUpdate(Seat existingEntity) {
        String column = getStringInput("Enter new seat column or leave blank to keep current (" + existingEntity.getSeatColumn() + "): ").trim();
        if (!column.isEmpty()) {
            existingEntity.setSeatColumn(column);
        }

        Integer seatRow = getIntInputNullable("Enter new seat row or leave blank to keep current (" + existingEntity.getSeatRow() + "): ");
        if (seatRow != null) {
            existingEntity.setSeatRow(seatRow);
        }

        SeatStatus seatStatus = inputValidSeatStatus(Validator::isValidSeatStatus);
        if (seatStatus != null) {
            existingEntity.setSeatStatus(seatStatus);
        }

        SeatType seatType = inputValidSeatType(Validator::isValidSeatType);
        if (seatType != null) {
            existingEntity.setSeatType(seatType);
        }
    }
}
