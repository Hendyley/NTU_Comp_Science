package AdminModule;
import java.util.Scanner;

import MovieGoerModule.Cinema;
import MovieGoerModule.Cineplex;
import MovieGoerModule.ClassOfCinema;
import MovieGoerModule.TimeSlot;

public class timeslotChange {
    public static void change(Cineplex cine) {
        Scanner sc  = new Scanner(System.in);
        System.out.println("Enter the date of show:");
        String date = sc.nextLine();
        while(!dateChecker.check(date)){
            System.out.println("Invalid date! Try again!");
        }
        System.out.println("Enter the start time:");
        String startTime = sc.nextLine();
        System.out.println("Enter the end time:");
        String endTime = sc.nextLine();
        
        System.out.println("Enter the movieClass:");
        System.out.println("1: REGULAR");
        System.out.println("2: PLATINUM");
        System.out.println("3: DOLBY");
        int choice = sc.nextInt();
        ClassOfCinema movieClass;
        switch(choice){
            case 1:
                movieClass = ClassOfCinema.REGULAR;
                break;

            case 2:
                movieClass = ClassOfCinema.PLATINUM;
                break;

            case 3:
                movieClass = ClassOfCinema.DOLBY;
                break;

            default:
                movieClass = ClassOfCinema.REGULAR;
                break;
        }
        
        for(int i = 0; i < cine.getRoomList().size(); i++){
            //print id of rooms
        }
        System.out.println("Select a room:");
        int room = sc.nextInt();
        Cinema c = cine.getRoom(room);
        
        //TimeSlot ts = new TimeSlot(date, startTime, endTime, movieClass, c);
        // m.addSlot(ts);
    }
}
