package AdminModule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class changeHolidays {

    public void addDay(String date){
        try{
            ArrayList al = holidayTextDB.readHolidays();
            Holiday d = new Holiday(date);
            al.add(d);
            holidayTextDB.saveHoliday(al);
        } catch(Exception e){ 
            System.out.println("IOException > " + e.getMessage());
        }
        
    }

    public void removeDay() throws IOException{
        Scanner sc = new Scanner(System.in);
        ArrayList <Holiday> days = holidayTextDB.readHolidays();
        for(int i = 0; i < days.size(); i++){
            System.out.println(i + ": " + days.get(i).getDate());
        }

        int index = 0;
        while(true){
            System.out.println("Select index of date to remove (-1 to exit) ");
            index = sc.nextInt();
            sc.nextLine();  //clear buffer
            if(index == -1){
                System.out.println("Going back...");
            }
            else if(index < -1 || index >= days.size()){
                System.out.println("Invalid index! Try again!");
            }
            else break; //correct input
        }

        days.remove(index);
        holidayTextDB.saveHoliday(days);
        System.out.println("Date succesfully removed!");

        System.out.println("Enter 1 to print updated list");
        int print = 0;
        print = sc.nextInt();
        sc.nextLine();  //clear buffer
        if(print == 1){
            System.out.println("Updated list: ");
            holidayTextDB.printHolidays();
        }

    }
}
