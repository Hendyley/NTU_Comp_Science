package AdminModule;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class configureSettings {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        configure();
    }
    private final static String PRICELIST_FILE_NAME = "Pricelist.txt";
    private final static String VIEW_TOP5_SETTINGS_FILE_NAME = "ViewTop5Settings.txt";
    public static void configure() throws ClassNotFoundException, IOException {
        Scanner sc = new Scanner(System.in);
        int choice;

        do{
            System.out.println("Choose an option:");
            System.out.println("1: Change ticket price");
            System.out.println("2: Add/Remove holiday");
            System.out.println("3: Add/Remove showtimes");
            System.out.println("4: Change view top 5 settings for moviegoer");
            System.out.println("5: Back");
            choice = sc.nextInt();
            sc.nextLine();  //clear buffer
            switch(choice){
                case 1:
                    //change ticket price
                    //print pricelist
                    printPricelist();
                    //update pricelist
                    updatePricelist();
                    break;

                case 2:
                    //add holidays
                    changeHolidays change = new changeHolidays();
                    
                    System.out.println("Choose an option:");
                    System.out.println("1: Add holiday");
                    System.out.println("2: Remove holiday");
                    System.out.println("3: Back");
                    int addRemove = sc.nextInt();
                    sc.nextLine();  //clear buffer

                    switch(addRemove){
                        case 1:
                            String date;
                            System.out.println("Holiday list: ");
                            holidayTextDB.printHolidays();
                            
                            while(true){
                                System.out.println("Enter a holiday date (DD/MM/YYYY) or 0 to exit:");
                                date = sc.nextLine();
                                if(dateChecker.check(date)){ //check format
                                    if(!holidayTextDB.isExistingHoliday(date)){
                                        change.addDay(date);
                                        System.out.println("Holiday added successfully!");
                                        System.out.println("Enter 1 to print updated list");
                                        int print = 0;
                                        print = sc.nextInt();
                                        sc.nextLine();  //clear buffer
                                        if(print == 1){
                                            System.out.println("Updated list: ");
                                            holidayTextDB.printHolidays();
                                        }
                                        break;      //successfully added
                                    }
                                    else{
                                        System.out.println("Holiday already in database!");
                                    }
                                }
                                else if(date.compareTo("0") == 0) break;
                                else{
                                    System.out.println("Invalid date! Try again!");
                                }
                            }
                            break;  //break add

                        case 2:
                            change.removeDay();
                            break;  //break remove

                        case 3:
                            System.out.println("Going back...");
                            break;

                        default:
                            System.out.println("Invalid number! Try again!");
                            break;
                    }


                    break;  //break case
                    

                case 3:
                    // add/delete timeslots
                    System.out.println("Select:");
                    System.out.println("1: Add Time Slot");
                    System.out.println("2: Remove Time Slot");
                    System.out.println("3: Back");
                    int select = sc.nextInt();
                    sc.nextLine(); //clear buffer
                    switch(select){
                        case 1:
                            addTimeslot.addTS();
                            break;

                        case 2:
                            deleteTimeSlot.delete();
                            break;

                        case 3:
                            System.out.println("Going back...");
                            break;

                        default:
                            System.out.println("Invalid number! Try again!");
                            break;

                    }

                    break;

                case 4:
                    //change permissions to view
                    //admin can view both by sales and by ratings
                    updateViewTop5Settings();
                    break;

                case 5:
                    System.out.println("Going back...");
                    break;

                default:
                    System.out.println("Invalid choice! Try again!");
            }
        } while(choice != 5);

    }

    public static ArrayList<Object> getPricelistFromFile() throws IOException, ClassNotFoundException{
        ArrayList<Object> priceList;

        try {
            FileInputStream fileInputStream2 = new FileInputStream(PRICELIST_FILE_NAME);
            ObjectInputStream objectInputStream2 = new ObjectInputStream(fileInputStream2);
            priceList = (ArrayList<Object>) objectInputStream2.readObject();
            objectInputStream2.close();
            System.out.println("Pricelist retrieved from File");

        } catch (FileNotFoundException e) {
            FileOutputStream fileOutputStream = new FileOutputStream(PRICELIST_FILE_NAME);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            priceList = generatePriceList();
            objectOutputStream.writeObject(priceList);
            objectOutputStream.flush();
            objectOutputStream.close();
            System.out.println("Pricelist File not found, creating new Pricelist File");
        }

        return priceList;
    }

    public static void addPricelistToFile(ArrayList<Object> priceList) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(PRICELIST_FILE_NAME);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(priceList);
        objectOutputStream.flush();
        objectOutputStream.close();
        System.out.printf("Pricelist added to File\n");
    }


    private static ArrayList<Object> generatePriceList() throws ClassNotFoundException, IOException{
        ArrayList<Object> priceList = new ArrayList<>();
        LinkedHashMap<String,Double> ageList = new LinkedHashMap<String,Double>();
        LinkedHashMap<String,Double> seatTypeList = new LinkedHashMap<String,Double>();
        LinkedHashMap<String,Double> cinemaClassList = new LinkedHashMap<String,Double>();
        LinkedHashMap<String,Double> movieTypeList = new LinkedHashMap<String,Double>();
        LinkedHashMap<String,Double> dayList = new LinkedHashMap<String,Double>();

        ageList.put("CHILD", 2.0);
        ageList.put("STUDENT", 3.0);
        ageList.put("SENIOR", 2.0);
        ageList.put("ADULT", 4.0);

        seatTypeList.put("SEAT",2.0);
        seatTypeList.put("COUPLE_SEAT",4.0);
        seatTypeList.put("ELITE_SEAT",5.0);
        seatTypeList.put("ULTIMA_SEAT",7.0);

        cinemaClassList.put("REGULAR",3.0);
        cinemaClassList.put("DOLBY",6.0);
        cinemaClassList.put("PLATINUM",10.0);

        movieTypeList.put("REGULAR_2D",3.0);
        movieTypeList.put("REGULAR_3D",4.5);
        movieTypeList.put("BLOCKBUSTER_2D",5.0);
        movieTypeList.put("BLOCKBUSTER_3D",6.0);


        dayList.put("MON_TO_WED",2.0);
        dayList.put("THURS",1.5);
        dayList.put("FRI_BEFORE_6",3.0);
        dayList.put("HOLIDAY",6.0);
        dayList.put("REMAINING_DAYS",4.0);
        
        
        priceList.add(ageList);
        priceList.add(seatTypeList);
        priceList.add(cinemaClassList);
        priceList.add(movieTypeList);
        priceList.add(dayList);
        
        return priceList;
    }

    private static void printPricelist() throws ClassNotFoundException, IOException{
        ArrayList<Object> pricelist = getPricelistFromFile();
        LinkedHashMap<String,Double> ageList = (LinkedHashMap<String,Double>)pricelist.get(0);
        LinkedHashMap<String,Double> seatTypeList = (LinkedHashMap<String,Double>)pricelist.get(1);
        LinkedHashMap<String,Double> cinemaClassList = (LinkedHashMap<String,Double>)pricelist.get(2);
        LinkedHashMap<String,Double> movieTypeList = (LinkedHashMap<String,Double>)pricelist.get(3);
        LinkedHashMap<String,Double> dayList = (LinkedHashMap<String,Double>)pricelist.get(4);

        System.out.println("Printing out Pricelist\n");
        System.out.println("Age");
        System.out.println("------------------");
        for (Map.Entry<String, Double> age : ageList.entrySet()) {
            String key = age.getKey();
            Double value = age.getValue();
            System.out.printf("%s: %.2f\n",key,value);
        }
        System.out.println("");

        System.out.println("Seat Type");
        System.out.println("------------------");
        for (Map.Entry<String, Double> seatType : seatTypeList.entrySet()) {
            String key = seatType.getKey();
            Double value = seatType.getValue();
            System.out.printf("%s: %.2f\n",key,value);
        }
        System.out.println("");


        System.out.println("Cinema Class");
        System.out.println("------------------");
        for (Map.Entry<String, Double> cinemaClass : cinemaClassList.entrySet()) {
            String key = cinemaClass.getKey();
            Double value = cinemaClass.getValue();
            System.out.printf("%s: %.2f\n",key,value);
        }
        System.out.println("");

        System.out.println("Movie Type");
        System.out.println("------------------");
        for (Map.Entry<String, Double> movieType : movieTypeList.entrySet()) {
            String key = movieType.getKey();
            Double value = movieType.getValue();
            System.out.printf("%s: %.2f\n",key,value);
        }
        System.out.println("");

        System.out.println("Day");
        System.out.println("------------------");
        for (Map.Entry<String, Double> day : dayList.entrySet()) {
            String key = day.getKey();
            Double value = day.getValue();
            System.out.printf("%s: %.2f\n",key,value);
        }
        System.out.println("");

    }

    public static void updatePricelist() throws ClassNotFoundException, IOException{
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        ArrayList<Object> pricelist = getPricelistFromFile();
        LinkedHashMap<String,Double> ageList = (LinkedHashMap<String,Double>)pricelist.get(0);
        LinkedHashMap<String,Double> seatTypeList = (LinkedHashMap<String,Double>)pricelist.get(1);
        LinkedHashMap<String,Double> cinemaClassList = (LinkedHashMap<String,Double>)pricelist.get(2);
        LinkedHashMap<String,Double> movieTypeList = (LinkedHashMap<String,Double>)pricelist.get(3);
        LinkedHashMap<String,Double> dayList = (LinkedHashMap<String,Double>)pricelist.get(4);

        
        //select category to change
        do{
            System.out.println("What would like to change?");
            System.out.println("1: Age");
            System.out.println("2. Seat Type");
            System.out.println("3: Cinema Class");
            System.out.println("4: Movie Type");
            System.out.println("5. Day");
            System.out.println("6: Done");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    ageList = switch_case_age(ageList);
                    break;
                
                case 2:
                    seatTypeList = switch_case_seatType(seatTypeList);
                    break;
                
                case 3:
                    cinemaClassList = switch_case_cinemaClass(cinemaClassList);
                    break;

                case 4:
                    movieTypeList = switch_case_movieType(movieTypeList);
                    break;

                case 5:
                    dayList = switch_case_day(dayList);
                    break;

                case 6:
                    pricelist.set(0,ageList);
                    pricelist.set(1,seatTypeList);
                    pricelist.set(2,cinemaClassList);
                    pricelist.set(3,movieTypeList);
                    pricelist.set(4,dayList);
                    addPricelistToFile(pricelist);
                    System.out.println("Updated successfully!");
                    switch_case_printUpdatedPriceList();
                    
                default:
                    break;
            }
        }while(choice != 6);
    }

    private static LinkedHashMap<String,Double> switch_case_age(LinkedHashMap<String,Double> ageList){
        int choice = 0;
        double newPrice = 0;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Select age to change");
            System.out.println("1: CHILD");
            System.out.println("2: STUDENT");
            System.out.println("3: SENIOR");
            System.out.println("4: ADULT");
            System.out.println("5: Back");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter new price for CHILD:");
                    newPrice = sc.nextDouble();
                    ageList.replace("CHILD", newPrice);
                    break;
                
                case 2:
                    System.out.println("Enter new price for STUDENT:");
                    newPrice = sc.nextDouble();
                    ageList.replace("STUDENT", newPrice);
                    break;

                case 3:
                    System.out.println("Enter new price for SENIOR:");
                    newPrice = sc.nextDouble();
                    ageList.replace("SENIOR", newPrice);
                    break;

                case 4:
                    System.out.println("Enter new price for ADULT:");
                    newPrice = sc.nextDouble();
                    ageList.replace("ADULT", newPrice);
                    break;
                
                case 5:
                    System.out.println("Going Back...\n");
                    break;

                default:
                    break;
            }

        }while(choice < 1 || choice > 5);

        return ageList;
    }

    private static LinkedHashMap<String,Double> switch_case_seatType(LinkedHashMap<String,Double> seatTypeList){
        int choice = 0;
        double newPrice = 0;
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println("Select seat type to change");
            System.out.println("1: SEAT");
            System.out.println("2: COUPLE_SEAT");
            System.out.println("3: ELITE_SEAT");
            System.out.println("4: ULTIMA_SEAT");
            System.out.println("5: Back");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter new price for SEAT");
                    newPrice = sc.nextDouble();
                    seatTypeList.replace("SEAT", newPrice);
                    break;
            
                case 2:
                    System.out.println("Enter new price for COUPLE_SEAT");
                    newPrice = sc.nextDouble();
                    seatTypeList.replace("COUPLE_SEAT", newPrice);
                    break;

                case 3:
                    System.out.println("Enter new price for ELITE_SEAT");
                    newPrice = sc.nextDouble();
                    seatTypeList.replace("ELITE_SEAT", newPrice);
                    break;

                case 4:
                    System.out.println("Enter new price for ULTIMA_SEAT");
                    newPrice = sc.nextDouble();
                    seatTypeList.replace("ULTIMA_SEAT", newPrice);
                    break;

                case 5:
                    System.out.println("Going Back...\n");
                    break;

                default:
                    break;
            }

        } while (choice < 1 || choice > 5);

        return seatTypeList;
    }

    private static LinkedHashMap<String,Double> switch_case_cinemaClass(LinkedHashMap<String,Double> cinemaClassList){
        int choice = 0;
        double newPrice = 0;
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println("Select cinema class to change");
            System.out.println("1: REGULAR");
            System.out.println("2: DOLBY");
            System.out.println("3: PLATINUM");
            System.out.println("4: Back");
            
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter new price for REGULAR");
                    newPrice = sc.nextDouble();
                    cinemaClassList.replace("REGULAR", newPrice);
                    break;
            
                case 2:
                    System.out.println("Enter new price for DOLBY");
                    newPrice = sc.nextDouble();
                    cinemaClassList.replace("DOLBY", newPrice);                   
                    break;
                
                case 3:
                    System.out.println("Enter new price for PLATINUM");
                    newPrice = sc.nextDouble();
                    cinemaClassList.replace("PLATINUM", newPrice);
                    break;
                
                case 4:
                    System.out.println("Going Back...\n");
                    break;
            
                default:
                    break;
            }
        } while (choice < 1 || choice > 4);

        return cinemaClassList;
    }   

    private static LinkedHashMap<String,Double> switch_case_movieType(LinkedHashMap<String,Double> movieTypeList){
        int choice = 0;
        double newPrice = 0;
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println("Select movie type to change");
            System.out.println("1: REGULAR_2D");
            System.out.println("2: REGULAR_3D");
            System.out.println("3: BLOCKBUSTER_2D");
            System.out.println("4: BLOCKBUSTER_3D");
            System.out.println("5: Back");
            choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.println("Enter new price for REGULAR_2D");
                    newPrice = sc.nextDouble();
                    movieTypeList.replace("REGULAR_2D", newPrice);
                    break;
            
                case 2:
                    System.out.println("Enter new price for REGULAR_3D");
                    newPrice = sc.nextDouble();
                    movieTypeList.replace("REGULAR_3D", newPrice);                   
                    break;
                
                case 3:
                    System.out.println("Enter new price for BLOCKBUSTER_2D");
                    newPrice = sc.nextDouble();
                    movieTypeList.replace("BLOCKBUSTER_2D", newPrice);
                    break;
                
                case 4:
                    System.out.println("Enter new price for BLOCKBUSTER_3D");
                    newPrice = sc.nextDouble();
                    movieTypeList.replace("BLOCKBUSTER_3D", newPrice);
                    break;

                case 5:
                    System.out.println("Going Back...\n");
                    break;
                
                default:
                    break;
            }
        } while (choice < 1 || choice > 5);

        return movieTypeList;
    }   


    private static LinkedHashMap<String,Double> switch_case_day(LinkedHashMap<String,Double> dayList){
        int choice = 0;
        double newPrice = 0;
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println("Select day to change");
            System.out.println("1: MON_TO_WED");
            System.out.println("2: THURS");
            System.out.println("3: FRI_BEFORE_6");
            System.out.println("4: HOLIDAY");
            System.out.println("5: REMAINING_DAYS");
            System.out.println("6: Back");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter new price for MON_TO_WED");
                    newPrice = sc.nextDouble();
                    dayList.replace("MON_TO_WED", newPrice);
                    break;
            
                case 2:
                    System.out.println("Enter new price for THURS");
                    newPrice = sc.nextDouble();
                    dayList.replace("THURS", newPrice);                   
                    break;
                
                case 3:
                    System.out.println("Enter new price for FRI_BEFORE_6");
                    newPrice = sc.nextDouble();
                    dayList.replace("FRI_BEFORE_6", newPrice);
                    break;
                
                case 4:
                    System.out.println("Enter new price for HOLIDAY");
                    newPrice = sc.nextDouble();
                    dayList.replace("HOLIDAY", newPrice);
                    break;

                case 5:
                    System.out.println("Enter new price for REMAINING_DAYS");
                    newPrice = sc.nextDouble();
                    dayList.replace("REMAINING_DAYS", newPrice);
                    break;

                case 6:
                    System.out.println("Going Back...\n");
                    break;

                default:
                    break;
            }
            
            
        } while (choice < 1 || choice > 6);


        return dayList;
    }

    private static void switch_case_printUpdatedPriceList() throws ClassNotFoundException, IOException{
        int choice = 0;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Print out updated pricelist?");
            System.out.println("1. Yes.");
            System.out.println("2. No.");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    printPricelist();
                    break;

                case 2:
                    break;

                default:
                    break;
            }

        } while (choice != 1 && choice != 2);

    }

    public static LinkedHashMap<String,Boolean> getViewTop5SettingsFromFile() throws IOException, ClassNotFoundException{
        LinkedHashMap<String,Boolean> viewTop5Settings;

        try {
            FileInputStream fileInputStream2 = new FileInputStream(VIEW_TOP5_SETTINGS_FILE_NAME);
            ObjectInputStream objectInputStream2 = new ObjectInputStream(fileInputStream2);
            viewTop5Settings = (LinkedHashMap<String,Boolean>) objectInputStream2.readObject();
            objectInputStream2.close();
            System.out.println("viewTop5Settings retrieved from File");

        } catch (FileNotFoundException e) {
            FileOutputStream fileOutputStream = new FileOutputStream(VIEW_TOP5_SETTINGS_FILE_NAME);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            viewTop5Settings = generateViewTop5Settings();
            objectOutputStream.writeObject(viewTop5Settings);
            objectOutputStream.flush();
            objectOutputStream.close();
            System.out.println("viewTop5Settings File not found, creating new viewTop5Settings File");
        }

        return viewTop5Settings;
    }

    public static void addViewTop5SettingsToFile(LinkedHashMap<String,Boolean> viewTop5Settings) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(VIEW_TOP5_SETTINGS_FILE_NAME);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(viewTop5Settings);
        objectOutputStream.flush();
        objectOutputStream.close();
        System.out.printf("viewTop5Settings added to File\n");
    }

    private static LinkedHashMap<String,Boolean> generateViewTop5Settings(){
        LinkedHashMap<String,Boolean> viewTop5Settings = new LinkedHashMap<String,Boolean>();
        viewTop5Settings.put("VIEW_BY_SALES",true);
        viewTop5Settings.put("VIEW_BY_RATING",true);
        return viewTop5Settings;
    }


    private static void printViewTop5Settings() throws ClassNotFoundException, IOException{
        LinkedHashMap<String,Boolean> viewTop5Settings = getViewTop5SettingsFromFile();

        System.out.println("View Top 5 Settings for MovieGoer");
        System.out.println("---------------------------------");
        for (Map.Entry<String, Boolean> seatType : viewTop5Settings.entrySet()) {
            String key = seatType.getKey();
            Boolean value = seatType.getValue();
            System.out.printf("%s: %s\n",key,value ? "true" : "false");
        }
        System.out.println("");
    }

    private static void updateViewTop5Settings() throws IOException, ClassNotFoundException{
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        LinkedHashMap<String,Boolean> viewTop5Settings = getViewTop5SettingsFromFile();
        
        System.out.println("Original Settings:");
        //print view5topsettings
        printViewTop5Settings();
        do {
            System.out.println("What would you like to change?");
            System.out.println("1. View by Sales for MovieGoer");
            System.out.println("2. View by Rating for MovieGoer");
            System.out.println("3. Done");
            choice = sc.nextInt();
            //clear buffer
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("View by Sales for MovieGoer");
                    System.out.println("1. True");
                    System.out.println("2. False");
                    System.out.println("Select option:");
                    choice = sc.nextInt();
                    //clear buffer
                    sc.nextLine();

                    switch (choice) {
                        case 1:
                            viewTop5Settings.replace("VIEW_BY_SALES",true);
                            break;
                        
                        case 2:
                            viewTop5Settings.replace("VIEW_BY_SALES",false);
                            break;

                        default:
                            break;
                    }

                    break;
                
                case 2:
                    System.out.println("View by Rating for MovieGoer");
                    System.out.println("1. True");
                    System.out.println("2. False");
                    System.out.println("Select option:");
                    choice = sc.nextInt();
                    //clear buffer
                    sc.nextLine();
                    switch (choice) {
                        case 1:
                            viewTop5Settings.replace("VIEW_BY_RATING",true);
                            break;
                        
                        case 2:
                            viewTop5Settings.replace("VIEW_BY_RATING",false);
                            break;

                        default:
                            break;
                    }
                    break;

                case 3:
                    if((!viewTop5Settings.get("VIEW_BY_SALES")) && (!viewTop5Settings.get("VIEW_BY_RATING"))){
                        System.out.println("Update Failed. One of the viewing option has to be true");
                        choice = 0;
                        break;
                    }
                    addViewTop5SettingsToFile(viewTop5Settings);
                    System.out.println("Updated successfully!");
                    switch_case_printUpdatedVT5S();
                    break;

                default:
                    break;
            }

        } while (choice != 3);
    }

    private static void switch_case_printUpdatedVT5S() throws ClassNotFoundException, IOException{
        int choice = 0;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Print out updated view top 5 settings?");
            System.out.println("1. Yes.");
            System.out.println("2. No.");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    printViewTop5Settings();
                    break;

                case 2:
                    break;

                default:
                    break;
            }

        } while (choice != 1 && choice != 2);       
    }

}


