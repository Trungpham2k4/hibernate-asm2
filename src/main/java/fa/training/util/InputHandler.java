package fa.training.util;

import fa.training.enums.SeatStatus;
import fa.training.enums.SeatType;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.function.Function;

public class InputHandler {

    private static final Scanner scanner = new Scanner(System.in);

    public static int inputValidOption(int min, int max){
        while(true){
            int option = getIntInput("Please input an option from " + min + " to " + max + ": ");
            if(option < min || option > max){
                System.out.println("Invalid option. Please provide a number between " + min + " and " + max);
            }else{
                return option;
            }
        }
    }

    public static int inputValidIntField(String prompt, String message, Function<Integer, Boolean> validator){
        while(true){
            int input = getIntInput(prompt);
            if(validator.apply(input)){
                return input;
            }
            System.out.println(message);
        }
    }

    public static SeatStatus inputValidSeatStatus(Function<String, Boolean> validator){
        while (true){
            String input = getStringInput("Enter seat status (AVAILABLE, NOT_AVAILABLE, BOOKED) or leave blank ").toUpperCase().trim();
            if(validator.apply(input)){
                if(input.isEmpty()){
                    return null;
                }
                return SeatStatus.valueOf(input);
            }
            System.out.println("Invalid seat status. Please enter AVAILABLE, NOT_AVAILABLE, or BOOKED.");
        }
    }

    public static SeatType inputValidSeatType(Function<String, Boolean> validator){
        while (true){
            String input = getStringInput("Enter seat type (NORMAL, VIP) or leave blank: ").toUpperCase().trim();
            if(validator.apply(input)){
                if (input.isEmpty()){
                    return null;
                }
                return SeatType.valueOf(input);
            }
            System.out.println("Invalid seat type. Please enter NORMAL or VIP.");
        }
    }

    public static LocalDate inputValidDate(Function<String, Boolean> validator){
        while (true){
            String input = getStringInput("Enter date (YYYY-MM-DD) or leave blank: ").trim();
            if(validator.apply(input)){
                if(input.isEmpty()){
                    return null;
                }
                return LocalDate.parse(input, Constant.DATE_FORMATTER);
            }
            System.out.println("Invalid date. Please enter YYYY-MM-DD.");
        }
    }

    public static int getIntInput(String prompt){
        while(true){
            try{
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Please enter an integer");
            }
        }
    }

    public static Integer getIntInputNullable(String prompt){
        while(true){
            System.out.print(prompt);
            String input = scanner.nextLine();
            if(!Validator.isNotBlank(input)){
                return null;
            }
            try{
                if(Validator.isPositive(Integer.parseInt(input))){
                    return Integer.parseInt(input);
                }else{
                    System.out.println("Please enter a positive integer or leave blank for null");
                }
            }catch (NumberFormatException e){
                System.out.println("Please enter an integer or leave blank for null");
            }
        }
    }

    public static String getStringInput(String prompt){
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
