import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

import MovieGoerModule.*;
import AdminModule.*;
import static AdminModule.configureSettings.getPricelistFromFile;

public class MoblimaApp {
    private final static String ACCOUNT_FILE_NAME = "AccountList.txt";
    private final static String GUEST_TRANSACTION_FILE_NAME = "guestTransaction.txt";
    private static Scanner sc = new Scanner(System.in);
    private static Role currentRole;
    private static boolean isLogined = false;
    private static boolean isGuest = false;
    private static int currentMGIndex = -1;
    // private static String[] holidays = { "01/01/2022", "01/02/2022", "02/02/2022", "15/04/2022", "01/05/2022", "03/05/2022",
    // "15/05/2022", "10/07/2022", "09/08/2022", "24/10/2022", "25/12/2022" };
    private static ArrayList<Holiday> holidayList;
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
    private static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) throws ClassNotFoundException, IOException{
        holidayList   = holidayTextDB.readHolidays();
        int loginOption;

        generateDummyData();
        printAccountLists();
        do{
            System.out.println("    MoblimaApp    ");
            System.out.println("********************");
            System.out.println("1. Login into Account");
            System.out.println("2. Continue as guest");
            System.out.println("3. Exit");
            System.out.println("********************");
            loginOption = sc.nextInt();
            sc.nextLine();

            switch(loginOption){
                case 1:
                    switch_case_login();
                    break;
                case 2:
                    isGuest = true;
                    switch_case_moviegoer();
                    break;
                default:
                    break;
            } 
        }while(loginOption != 3);

        sc.close();
    }

    public static ArrayList<Object> getAccountListsFromFile() throws IOException, ClassNotFoundException {
        ArrayList<Object> accountLists = new ArrayList<Object>();
        ArrayList<Object> staffList = new ArrayList<Object>();
        ArrayList<Object> mgList = new ArrayList<Object>();;
        accountLists.add(staffList);
        accountLists.add(mgList);
               
        try {
            FileInputStream fileInputStream2 = new FileInputStream(ACCOUNT_FILE_NAME);
            ObjectInputStream objectInputStream2 = new ObjectInputStream(fileInputStream2);
            accountLists = (ArrayList<Object>) objectInputStream2.readObject();
            objectInputStream2.close();
            System.out.println("Account Lists retrieved from File");

        } catch (FileNotFoundException e) {
            FileOutputStream fileOutputStream = new FileOutputStream(ACCOUNT_FILE_NAME);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(accountLists);
            objectOutputStream.flush();
            objectOutputStream.close();
            System.out.println("Account List File not found, creating new Account List File");
        }

        return accountLists;
    }

    public static void addAccountListsToFile(ArrayList<Object> accountLists) throws IOException {
            FileOutputStream fileOutputStream = new FileOutputStream(ACCOUNT_FILE_NAME);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(accountLists);
            objectOutputStream.flush();
            objectOutputStream.close();
            System.out.printf("Account Lists added to File\n");
    }

    public static boolean isUsernameExist(ArrayList<Object> accountLists,String username){
        ArrayList<Staff> staffList = (ArrayList<Staff>)accountLists.get(0);
        ArrayList<MovieGoer> mgList = (ArrayList<MovieGoer>)accountLists.get(1);

        for (Staff s : staffList) {
            if (s.getUsername().equals(username)) {
                return true;
            }
        }

        for (MovieGoer mg : mgList) {
            if (mg.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private static int getMGIndex(ArrayList<MovieGoer> mgList, String username){
        for(int i = 0; i < mgList.size(); i++){
            if(mgList.get(i).getUsername().equals(username)){
                return i;
            }
        }
        return -1;
    }

    private static void generateDummyData() throws ClassNotFoundException, IOException{
        ArrayList<Object> accountLists = getAccountListsFromFile();
        ArrayList<Staff> staffList = (ArrayList<Staff>)accountLists.get(0);
        ArrayList<MovieGoer> mgList = (ArrayList<MovieGoer>)accountLists.get(1);
        Staff staff1;
        MovieGoer mg1;

        
        staff1 = new Staff("admin", "admin123");
        if(!isUsernameExist(accountLists, staff1.getUsername())){
            staffList.add(staff1);
        }

        mg1 = new MovieGoer("user1", "user123", "Adams",95463461,"adamsOKorNot@gmail.com");
        mg1.setAge(19);
        if(!isUsernameExist(accountLists, mg1.getUsername())){            
            mgList.add(mg1);
        }

        mg1 = new MovieGoer("user2","user234","John",94352423,"johnIsOk@gmail.com");
        mg1.setAge(31);
        if(!isUsernameExist(accountLists, mg1.getUsername())){
            mgList.add(mg1);
        }

        accountLists.set(0,staffList); 
        accountLists.set(1,mgList);
        addAccountListsToFile(accountLists);
    }

    private static void printAccountLists() throws ClassNotFoundException, IOException{
        ArrayList<Object> accountLists = getAccountListsFromFile();
        ArrayList<Staff> staffList = (ArrayList<Staff>)accountLists.get(0);
        ArrayList<MovieGoer> mgList = (ArrayList<MovieGoer>)accountLists.get(1);

        System.out.println("Printing out Staff List");
        for(Staff s : staffList){
            System.out.printf("username: %s ,password: %s ,role: %s\n",s.getUsername(),s.getPassword(),s.getRole());
        }
        System.out.println("");
        System.out.println("Printing out MovieGoer List");
        for(MovieGoer mg : mgList){
            System.out.printf("username: %s ,password: %s ,role: %s\n",mg.getUsername(),mg.getPassword(),mg.getRole());
            System.out.printf("name: %s ,email: %s ,mobile: %s\n",mg.getName(),mg.getEmail(),mg.getMobile());
            System.out.printf("age: %s\n", String.valueOf(mg.getAge()) );
            System.out.printf("Transaction List: %s \n",mg.getTransactionHistory());
        }
        
    }

    public static boolean login(ArrayList<Object> accountLists, String username, String password) {
        ArrayList<Staff> staffList = (ArrayList<Staff>)accountLists.get(0);
        ArrayList<MovieGoer> mgList = (ArrayList<MovieGoer>)accountLists.get(1);

        for (Staff s : staffList) {
            if (s.getUsername().equals(username)
                    && s.getPassword().equals(password)) {
                //for testing purpose
                //s.printUserDetails();
                System.out.println("Login Success");
                System.out.println();
                currentRole = Role.ADMIN;
                isLogined = true;
                return true;
            }
        }

        for (MovieGoer mg : mgList) {
            if (mg.getUsername().equals(username)
                    && mg.getPassword().equals(password)) {
                //for testing purpose
                //mg.printUserDetails();
                System.out.println("Login Success");
                System.out.println();
                currentMGIndex = getMGIndex(mgList,username);
                currentRole = Role.MOVIEGOER;
                isLogined = true;
                return true;
            }
        }
        

        System.out.println("Incorrect Username or Password");
        System.out.println();
        return false;
    }


    public static ArrayList<Transaction> getTransListFromFile() throws IOException, ClassNotFoundException{
        ArrayList<Transaction> transList = new ArrayList<Transaction>();
    
        try{
            FileInputStream fileInputStream2 = new FileInputStream(GUEST_TRANSACTION_FILE_NAME);
            ObjectInputStream objectInputStream2 = new ObjectInputStream(fileInputStream2);
            transList = (ArrayList<Transaction>) objectInputStream2.readObject();
            objectInputStream2.close();
            System.out.println("Guest Transaction List retrieved from File");           
        }catch(FileNotFoundException e){
            FileOutputStream fileOutputStream = new FileOutputStream(GUEST_TRANSACTION_FILE_NAME);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(transList);
            objectOutputStream.flush();
            objectOutputStream.close();
            System.out.println("Guest Transaction List File not found, creating new Guest Transaction List File");           
        }
    
        return transList;
    }

    public static void addTransListToFile(ArrayList<Transaction> transList) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(GUEST_TRANSACTION_FILE_NAME);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(transList);
        objectOutputStream.flush();
        objectOutputStream.close();
        System.out.printf("Guest Transaction List added to File\n");
    }
    
    public static int getTransIndex(ArrayList<Transaction> transList, String transID){
        String currentTransID = "";
        for(int i = 0; i < transList.size(); i++){
            currentTransID = transList.get(i).getId();
           if(currentTransID.equals(transID)){
                return i;
           };
        }
        return -1;
    }
    
    public static void printTransList() throws ClassNotFoundException, IOException{
        ArrayList<Transaction> transList = getTransListFromFile();
    
        System.out.println();
        System.out.println("Printing out Guest Transaction List");
        for(Transaction trans : transList){
            System.out.printf("Transaction ID: %s", trans.getId());
            System.out.printf("Name: %s, Mobile: %d, Email: %s", trans.getMgName(), trans.getMgMobile(), trans.getMgEmail());
            System.out.printf("$ %.2f",trans.getTotalPrice());
            trans.printTickets();
        }
        System.out.printf("\n\n");
    
    }

    public static void switch_case_login() throws ClassNotFoundException, IOException{
        String username;
        String password;

        System.out.println("Login");
        System.out.print("Username:");
        username = sc.nextLine();
        System.out.print("Password:");
        password = sc.nextLine();
        ArrayList<Object> accountLists = getAccountListsFromFile();

        if(!login(accountLists,username,password)){
            return;
        }

        if(currentRole == Role.ADMIN){
            switch_case_admin();
        }else{
            switch_case_moviegoer();
        }

    }


    private static void switch_case_admin() throws ClassNotFoundException, IOException{
        int choice;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("********************");
            System.out.println("Admin Module");
            System.out.println("What would you like to do?");
            System.out.println("1: Add a new movie");
            System.out.println("2: Update an existing movie");
            System.out.println("3: Delete a movie");
            System.out.println("4: Configure system settings");
            System.out.println("5: View movies");
            System.out.println("6: View Top 5 Movies");
            System.out.println("7: Log Out");
            System.out.println("********************");
            choice = sc.nextInt();
            sc.nextLine(); //clear buffer

            switch(choice){
                case 1:
                    //add movie
                    createMovie.create();
                    break;

                case 2:
                    //update movie
                    updateMovie.update();
                    break;

                case 3:
                    //delete movie
                    deleteMovie.delete();
                    break;

                case 4:
                    //configure settings (change ticket prices, holidays, movie type)
                    configureSettings.configure();
                    break;

                case 5:
                    //view all movies
                    MovieDB.printMovieList();
                    break;

                case 6:
                    //view top
                    viewTop.top5(isLogined,currentRole);
                    break;

                case 7:
                    //log out
                    isLogined = false;
                    System.out.println("Logged Out Successfully!");
                    break;

                default:
                // System.out.println("testing case for now...");
                // printTransList();
                // ArrayList<Cineplex> ccineList = cineplexDB.getCineplexListFromFile();
                // ArrayList<Transaction> tLists = getTransListFromFile();
                // ArrayList<Object> aLists = getAccountListsFromFile();
                // ArrayList<Movie> mmLists = MovieDB.getMovieListFromFile();
                // ArrayList<Object> ppriceList = configureSettings.getPricelistFromFile();
                // LinkedHashMap<String,Boolean> vt5settings = configureSettings.getViewTop5SettingsFromFile();
                // System.out.println("end of testing case...");

                    break;
            }
            
        } while(choice != 7);
        
    }

    private static void switch_case_moviegoer() throws ClassNotFoundException, IOException{
        ArrayList<Cineplex> cathay;
        Scanner sc = new Scanner(System.in);

        int option = 0, cinema = 0, choice = 0, Qty = 0;
        float time;
        String key, datetime, movie, seatdesc;
        Cineplex cinename;
        ArrayList<Movie> movieList;
        Scanner s = new Scanner(System.in);
        do {
            cathay  = cineplexDB.getCineplexListFromFile();

            System.out.println("********************");
            System.out.println("Movie Goer Module");
            System.out.println("1. Search/List movie");
            System.out.println("2. View Movie details");
            System.out.println("3. Seat Availability and Booking");
            System.out.println("4. Book a ticket");
            System.out.println("5. View Booking History");
            System.out.println("6. List Top 5 Movies by sales OR by overall ratings");
    
            System.out.println("7. Give a movie review");
            if(isLogined){
                System.out.println("8. Log out");
            }else{
                System.out.println("8. End Guest Session");
            }
            System.out.println("********************");

            System.out.println("Enter Option");
            option = sc.nextInt();
            //clear buffer
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.println("1. Search/List movie");
                   
                    movieList = getMovieList(cathay,sc);
                    do {
                        boolean isNotFound = true;
                        System.out.println("1. Search movie");
                        System.out.println("2. List movie");
                        System.out.println("3. Exit");
                        System.out.println("Enter a choice");
                        choice = sc.nextInt();
                         //clear buffer
                         sc.nextLine();

                        switch (choice) {
                            case 1:
                                System.out.println("Search by keyword: ");
                                key = s.nextLine();
                                System.out.println("Movie search results:");
                                for (int j = 0; j < movieList.size(); j++) {
                                    if (movieList.get(j).getTitle().toLowerCase().indexOf(key.toLowerCase()) != -1) {
                                        System.out.println(j + ". " + movieList.get(j).getTitle());
                                        isNotFound = false;
                                    }
                                }
                                break;
                            
                            case 2:
                                System.out.println("Movie lists:");
                                for (int j = 0; j < movieList.size(); j++) {
                                    System.out.println(j + ". " + movieList.get(j).getTitle()+" "+ movieList.get(j).getType()+" "+ movieList.get(j).getStatus()+" Duration:"+ movieList.get(j).getMovieDurationMin()+" Age restriction:"+  movieList.get(j).getAge_restriction());
                                    isNotFound = false;
                                }
                                break;
                            case 3:
                                System.out.println("Exiting Search/List movie...");
                                break;

                            default:
                                break;
                        }
                        
                        if(isNotFound  && (choice == 1 || choice == 2)){
                            System.out.println("No result found");
                        }
                        System.out.println("");                        
                    } while (choice != 3);

                    break;
                case 2:
                    System.out.println("2. View Movie details");
                    
                    movieList = getMovieList(cathay,sc);
                    if(movieList.size() < 1){
                        System.out.println("No movie available.");
                        break;
                    }

                    for (int i = 0; i < movieList.size(); i++) {
                        System.out.println(i + " Movie " + movieList.get(i).getTitle()+" "+ movieList.get(i).getStatus()+" "
                            +movieList.get(i).getType()+" "+movieList.get(i).getAge_restriction());
                    }
                    System.out.println("Select movie to view details");
                    choice = sc.nextInt();
                    if(choice>=movieList.size()){
                        System.out.println("Please Choose appropriate Movie!");
                        break;
                    }
                    System.out.println("Movie Details : ");
                    System.out.println("Movie Title: " + movieList.get(choice).getTitle());
                    System.out.println("Movie Status: " + movieList.get(choice).getStatus());
                    System.out.println("Movie Duration: "+ movieList.get(choice).getMovieDurationMin()+ " minutes");
                    System.out.println("Movie Age Requirement: "+ movieList.get(choice).getAge_restriction());
                    System.out.println("Movie Type: "+ movieList.get(choice).getType());
                    // System.out.println("Movie Rating: " + movieList.get(choice).getRating());
                    System.out.println("Movie Director: " + movieList.get(choice).getDirector());
                    System.out.println("Movie Synopsis: " + movieList.get(choice).getSynopsis());
                    System.out.println("Movie Airing time: ");
                    ArrayList<TimeSlot> showair = movieList.get(choice).getTimeSlots();
                    for(int i=0; i<showair.size(); i++){
                        System.out.println(showair.get(i).getairingtimeformat()+" "+showair.get(i).getRoom().getCinemaClass());
                    }
                    // System.out.println("Movie Review: ");
                    // ArrayList<Review> showreview = movieList.get(choice).getreviewlist();
                    // for(int i=0; i<showreview.size(); i++){
                    //     System.out.println("Comment: "+i+" "+showreview.get(i).getRemark());
                    // }
                    System.out.println("");
                    break;

                case 3:
                    ArrayList<String> dateList = new ArrayList<String>();
                    ArrayList<TimeSlot> slotList = new ArrayList<TimeSlot>();
                    ArrayList<TimeSlot> slotList2 = new ArrayList<TimeSlot>();
                    String dateSelection = "";
                    TimeSlot slotSelected;

                    System.out.println("3. Seat Availability and Booking");
                    System.out.println("Cineplex List:");
                    for (int i = 0; i < cathay.size(); i++) {
                        System.out.println(i + ". " + cathay.get(i).getName());
                    }
                    System.out.println("Select one of the cineplex index");
                    cinema = sc.nextInt();
                    if (cinema >= cathay.size()) {
                        System.out.println("Please Choose appropriate Cinema!");
                        break;
                    }
                    cinename = cathay.get(cinema);
                    // if (cinename.getTimeslots().size() == 0) {
                    // System.out.println("No Movie is airing in this cineplex");
                    // break;
                    // }
                    if(cinename.getMovieList().size() < 1){
                        System.out.println("No movie available");
                        break;
                    }
                    System.out.println("Which movie :");

                    for (int i = 0; i < cinename.getMovieList().size(); i++) {
                        if((cinename.getMovieList().get(i).getStatus() == Status.NOW_SHOWING)
                            || (cinename.getMovieList().get(i).getStatus() == Status.PREVIEW)){
                            System.out.println(i + " " + cinename.getMovieList().get(i).getTitle());
                        }
                    }
                    int movieSelection = sc.nextInt();
                    if (movieSelection >= cinename.getMovieList().size()) {
                        System.out.println("Please Choose appropriate Movie!");
                        break;
                    }
                    slotList = cinename.getMovieList().get(movieSelection).getTimeSlots();

                    if (slotList.size() == 0) {
                        System.out.println("No date Available");
                    } else {
                        System.out.println("Select a date");
                        String firstDate = slotList.get(0).getStringDate();
                        System.out.println(0+" " + slotList.get(0).getStringDate());
                        dateList.add(firstDate);
                        for (int i = 1; i < slotList.size(); i++) {
                            if (slotList.get(i - 1).getStringDate() != slotList.get(i).getStringDate()) {
                                dateList.add(slotList.get(i).getStringDate());
                                System.out.println(i + " " + slotList.get(i).getStringDate());
                                //System.out.println(" " + slotList.get(i).getStringDate());
                            }
                        }

                        int input = sc.nextInt();
                        if (input >= dateList.size()) {
                            System.out.println("Please Choose appropriate Date!");
                            break;
                        }
                        dateSelection = dateList.get(input);
                    }

                    System.out.println("Select Timeslot");
                    for (int i = 0; i < slotList.size(); i++) {
                        if (slotList.get(i).getStringDate() == dateSelection) {
                            slotList2.add(slotList.get(i));
                        }
                    }
                    for (int i = 0; i < slotList2.size(); i++) {
                        System.out.println(
                                i + " " + slotList2.get(i).getStartTime() + "-" +
                                        slotList2.get(i).getEndTime());
                    }

                    int inputSlot = sc.nextInt();
                    if (inputSlot >= slotList2.size()) {
                        System.out.println("Please Choose appropriate TimeSlot!");
                        break;
                    }
                    slotSelected = slotList2.get(inputSlot);
                    slotSelected.getRoom().printSeats();

                    seatdesc = slotSelected.getRoom().getseattypedesc();
                    String couples = seatdesc.substring(0,seatdesc.indexOf("Elite"));
                    String elite = seatdesc.substring(seatdesc.indexOf("Elite"),seatdesc.indexOf("Ulti"));
                    String ulti = seatdesc.substring(seatdesc.indexOf("Ulti"));
                    System.out.println(couples);
                    System.out.println(elite);
                    System.out.println(ulti);

                    break;
                case 4:
                    switch_case_4(cathay);

                    break;

                case 5:
                    if(isLogined){
                        ArrayList<Object> accountLists = getAccountListsFromFile();
                        ArrayList<MovieGoer> mgList = (ArrayList<MovieGoer>)accountLists.get(1);
                        MovieGoer man = mgList.get(currentMGIndex);
                        ArrayList<Transaction> temptrans = man.getTransactionHistory();

                        System.out.println("Here is your Transaction :");
                        if(temptrans.size() < 1){
                            System.out.println("No past transactions found.");
                            break;
                        }
                        for(int i=0;i<temptrans.size();i++){
                            System.out.println("Transaction "+temptrans.get(i).getId());
                            temptrans.get(i).printTickets();
                        }
                    }

                    if(isGuest){
                        ArrayList<Transaction> transList = getTransListFromFile();
                        String transID;
                        Boolean isFound = false;

                        System.out.println("Please enter your transaction id");
                        transID = sc.nextLine();
            
                        System.out.println("Here is your Transaction :");
                        for(Transaction trans : transList){
                            if(trans.getId().equals(transID)){
                                System.out.println("Transaction "+trans.getId());
                                trans.printTickets();
                                isFound = true;
                                break;
                            }
                        }     
                        if(!isFound){
                            System.out.println("No transaction found");
                        }
                         
                    }

                    break;

                case 6:
                    System.out.println("6. List Top 5 Movies by sales OR by overall ratings");
                    viewTop.top5(isLogined,currentRole);
                    break;

                case 7:
                    movieList = MovieDB.getMovieListFromFile();
                    int i;
                    int choose = -1;
                    int rating = -1;

                    System.out.println("7. Give a movie review");
                    System.out.println("List of all current movie with rating");
                    if(movieList.size() < 1){
                        System.out.println("No movie available");
                    }
                    for (i= 0; i < movieList.size(); i++) {
                        System.out.println(
                                i + " Movie " + movieList.get(i).getTitle() + " current rating " + movieList.get(i).getRating()
                                        + " number of reviewer: " + movieList.get(i).getnumberofreviewer());
                    }
                    //movie choice
                    do {
                        System.out.println("Choose a movie");
                        choose = sc.nextInt();
                    } while (choose < 0 || choose > i);
                    //rating choice
                    do {
                        System.out.println("Give a rating from 1-5");
                        rating = sc.nextInt();                       
                    } while (rating < 1 || rating > 5);

                    sc = new Scanner(System.in);
                    System.out.println("Give a review");
                    String review = sc.nextLine();

                    Review rv = new Review(rating, review);
                    movieList.get(choose).addReview(rv);
                    ArrayList<Review> reviewlist = movieList.get(choose).getreviewlist();
                    movieList.get(choose).updatereviewscore(reviewlist);

                    //update review and rating into movie.txt
                    MovieDB.addMovieListToFile(movieList);
                    System.out.println("Thank you for the review.");

                    break;

                case 8:
                    if(isLogined){
                        currentMGIndex= -1;
                        isLogined = false;
                        System.out.println("Logged Out Successfully!");
                    }

                    if(isGuest){
                        isGuest = false;
                        System.out.println("Guest Session Ended");
                    }
                    break;
                    
                default:
                    System.out.println("testing case for now...");
                    printTransList();
                    ArrayList<Cineplex> ccineList = cineplexDB.getCineplexListFromFile();
                    ArrayList<Transaction> tLists = getTransListFromFile();
                    ArrayList<Object> aLists = getAccountListsFromFile();
                    ArrayList<Movie> mmLists = MovieDB.getMovieListFromFile();
                    ArrayList<Object> ppriceList = configureSettings.getPricelistFromFile();
                    LinkedHashMap<String,Boolean> vt5settings = configureSettings.getViewTop5SettingsFromFile();
                    System.out.println("end of testing case...");
                    break;
            }

        } while (isLogined || isGuest);

    }


    private static ArrayList<Movie> getMovieList(ArrayList<Cineplex> cathay, Scanner sc){
        int i = 0;
        int index = -1;
        ArrayList<Movie> movieList;

        System.out.println("Cineplex List:");
        for(Cineplex cineplex :cathay){
            System.out.printf("%s. %s\n",i,cineplex.getName());
            i++;
        }
        
        do {
            System.out.println("Select one of the cineplex index");
            index = sc.nextInt();
        } while (index < 0 || index > cathay.size()-1);
       

       return movieList = cathay.get(index).getMovieList();
    }


    private static void switch_case_4(ArrayList<Cineplex> cathay) throws ClassNotFoundException, IOException{
        LocalDateTime now = LocalDateTime.now();
        ArrayList<String> datel = new ArrayList<String>();
        String dateS = "", seatdesc, mgName = "", mgEmail = "";
        int j = 0, choice = 0, Qty = 0, ageChoice = 0;
        int mgMobile = 0;
        int index = -1;
        AgeOfMovieGoer guestAge = AgeOfMovieGoer.CHILD;
        //account db
        ArrayList<Object> accountLists = getAccountListsFromFile();
        ArrayList<MovieGoer> mgList = (ArrayList<MovieGoer>)accountLists.get(1);
        MovieGoer mg = new MovieGoer(mgName, mgEmail);
        if(currentMGIndex != -1){
            mg = mgList.get(currentMGIndex);
        }
         
        //movie db
        ArrayList<Movie> globalMovieList = MovieDB.getMovieListFromFile();
        int globalMovieIndex;
        Movie globalMovie;
        //cineplex db
        Cineplex cine = null;
        int cineIndex;
        ArrayList<Movie> movieList;
        Movie movie;
        int localMovieIndex;
        String movieTitle;
        ArrayList<TimeSlot> ts1 = null;
        TimeSlot tss = null;
        //guest Transaction db
        ArrayList<Transaction> transList = getTransListFromFile();
        
        System.out.println("4. Book a ticket");
        System.out.println("Cineplex List:");
        for(Cineplex cineplex :cathay){
            System.out.printf("%s. %s\n",j,cineplex.getName());
            j++;
        }
        do {
            System.out.println("Select one of the cineplex index");
            index = sc.nextInt();
        } while (index < 0 || index > cathay.size()-1);
        cine = cathay.get(index);
        cineIndex = index;
        movieList = cine.getMovieList();

        if(movieList.size()==0){
            System.out.println("No Movie is airing in this cineplex");
            return;
        }

        System.out.println("Which movie :");
        for (int i = 0; i < movieList.size(); i++) {
            if( (movieList.get(i).getStatus() == Status.NOW_SHOWING || movieList.get(i).getStatus() == Status.PREVIEW) ){
                System.out.println(i + " " + movieList.get(i).getTitle() + " " + movieList.get(i).getStatus() );
            }
        }
        int moviechoice = sc.nextInt();
        if(moviechoice >= movieList.size()){
            System.out.println("Please Choose appropriate Movie!");
            return;
        }
        movie = movieList.get(moviechoice);
        ts1 = movie.getTimeSlots();
        movieTitle = movie.getTitle();
        //Age requirement check
        if (isLogined &&  mg.getAgetype().ordinal() < movie.getAge_restriction().ordinal() ) {
            System.out.println("Age requirement required to watch " + movie.getAge_restriction() + " Movie");
            return;
        }

        if(isGuest){
            do {
                System.out.println("Age List");
                System.out.println("1. CHILD");
                System.out.println("2. STUDENT");
                System.out.println("3. SENIOR");
                System.out.println("4. ADULT");   
                System.out.println("Select your age");
                ageChoice = sc.nextInt();
    
                switch (ageChoice) {
                    case 1:
                        guestAge = AgeOfMovieGoer.CHILD;
                        break;
    
                    case 2:
                        guestAge = AgeOfMovieGoer.STUDENT;
                        break;
                
                    case 3:
                        guestAge = AgeOfMovieGoer.SENIOR;
                        break;
    
                    case 4:
                        guestAge = AgeOfMovieGoer.ADULT;
                        break;               
                    default:
                        break;
                }                
            } while (ageChoice < 1 || ageChoice > 4);

            if (guestAge.ordinal() < movie.getAge_restriction().ordinal() ) {
                System.out.println("Age requirement required to watch " + movie.getAge_restriction() + " Movie");
                return;
            }

        }

        //Check booking type
        if(movie.getStatus() == Status.COMING_SOON || movie.getStatus() == Status.END_OF_SHOWING)
        if (ts1.size() == 0) {
            System.out.println("The movie is coming soon or not showing now");
            return;
        }

        System.out.println("Select a date");
        String firstDate = ts1.get(0).getStringDate();
        System.out.println(0+" " + ts1.get(0).getStringDate());
        datel.add(firstDate);
        for (int i = 1; i < ts1.size(); i++) {
            if (ts1.get(i - 1).getStringDate() != ts1.get(i).getStringDate()) {
                datel.add(ts1.get(i).getStringDate());
                System.out.println(i + " " + ts1.get(i).getStringDate());
                //System.out.println(" " + ts1.get(i).getStringDate());
            }
        }

        int input = sc.nextInt();
        dateS = datel.get(input);


        System.out.println("Select Timeslot");
        ArrayList<Integer> tsnum = new ArrayList<>();
        int slot = 0;
        for (int i = 0; i < ts1.size(); i++) {
            if (ts1.get(i).getStringDate() == dateS) {
                System.out.println(slot + " " + ts1.get(i).getStartTime() + "-" +ts1.get(i).getEndTime());
                tsnum.add(i); slot++;
            }
        }
        choice = sc.nextInt();

        if (choice >= tsnum.size()) {
            System.out.println("Please Choose appropriate timeslot!");
            return;
        }
        tss = ts1.get(tsnum.get(choice));
        //System.out.println("selected "+tsnum.get(choice));
        //tss.getRoom().printSeats();

        System.out.println("Select Qty: ");
        Qty = sc.nextInt();
        ArrayList<Integer> ticketagelist= new ArrayList<>();
        int studentFB = 0;
        int seniorFB = 0;
        for(int i=0;i<Qty;i++){
            System.out.println("Selecting Age Category for Customer "+i);
            System.out.println("0 for CHILD");
            System.out.println("1 for STUDENT");
            System.out.println("2 for SENIOR");
            System.out.println("3 for ADULT");
            int age = sc.nextInt();
            if( (age>3) || (age<0) || age<movie.getAge_restriction().ordinal() ){
                Qty=-1;
                break;
            }

            if(age==2){
                seniorFB++;
            }
            if(age==1){
                studentFB++;
            }

            ticketagelist.add(age);
        }
        //clear buffer
        sc.nextLine();
        if(Qty == -1){
            System.out.println("Age requirement required to watch " + movie.getAge_restriction() + " Movie");
            return;
        }

        LocalDate dt = LocalDate.parse(dateS, df);
        Day d;
        String x=" ";
        Integer starttime = Integer.parseInt( tss.getStartTime().substring(0,2) );
        Integer endtime = Integer.parseInt( tss.getEndTime().substring(0,2) );
        //System.out.println(starttime+endtime);
        if (dt.getDayOfWeek() == DayOfWeek.MONDAY || dt.getDayOfWeek() == DayOfWeek.TUESDAY || dt.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
            d = Day.MON_TO_WED;
            if(studentFB!=0){
                x=x+studentFB + " Free 12oz Coke ";
            }
            if(seniorFB!=0){
                x=x+seniorFB + " Free Tea / Coffee ";
            }
        } else if (dt.getDayOfWeek() == DayOfWeek.FRIDAY && ( ( (starttime<18) && (endtime<18) )   ) ) {
            d = Day.FRI_BEFORE_6;
            if(studentFB!=0){
                x=x+studentFB + " Free 12oz Coke ";
            }
            if(seniorFB!=0){
                x=x+seniorFB + " Free Tea / Coffee ";
            }
        } else if (dt.getDayOfWeek() == DayOfWeek.THURSDAY) {
            d = Day.THURS;
        } else {//if (dt.getDayOfWeek() == DayOfWeek.SATURDAY || dt.getDayOfWeek() == DayOfWeek.SUNDAY) {
            d = Day.HOLIDAY;
        }


        for(int i=0; i<holidayList.size();i++){
            String dateStr = holidayList.get(i).getDate();
            if(dateStr.indexOf(dateS) > -1){
                d = Day.HOLIDAY;
            }
        }



        String transid = "Cinema :"+cine.getName()+" Movie title :"+movie.getTitle()+" "+tss.getairingtimeformat()+" "+d+" "+tss.getRoom().getCinemaClass()+" "+movie.getType()+x;
        System.out.println(transid);
        seatdesc = tss.getRoom().getseattypedesc();
        //System.out.println("Seatdesc : "+seatdesc);
        String couples1 = seatdesc.substring(0,seatdesc.indexOf("Elite"));
        String elite1 = seatdesc.substring(seatdesc.indexOf("Elite"),seatdesc.indexOf("Ulti"));
        String ulti1 = seatdesc.substring(seatdesc.indexOf("Ulti"));
        movie.getTimeSlots().get(tsnum.get(choice)).getRoom().printSeats();
        //Cinema c = movie.getTimeSlots().get(tsnum.get(choice)).getRoom();
        System.out.println(couples1);
        System.out.println(elite1);
        System.out.println(ulti1);
        ArrayList<Seattype> selectedseattype = new ArrayList<>();
        System.out.println("Select Seats: (Example: A12, B9 )");
        for (int q = 0; q < Qty; q++) {
            String selectseat = sc.nextLine();

            int row = Integer.valueOf(selectseat.toLowerCase().substring(0, 1).charAt(0) - 96) - 1;
            int col = Integer.parseInt(selectseat.substring(1)) - 1 ;
            //System.out.println("Gettype : "+tss.getRoom().getseattype(row,col)+" "+row+" "+col+tss.getRoom().checkseat(row, col));
            if ((row<10) && (col<12) ) {
                if(!tss.getRoom().checkseat(row, col)){
                    if(tss.getRoom().getseattype(row,col).ordinal() != Seattype.SEAT.ordinal()){
                        q = q + 1;
                        if((q) >= Qty){
                            System.out.println("Sorry the seat required 2 seaters. Choose again!");
                            q = q - 2;
                        } else{
                            tss.getRoom().book(row, col);
                            selectedseattype.add(tss.getRoom().getseattype(row,col));
                            selectedseattype.add(tss.getRoom().getseattype(row,col));
                            System.out.println("Double seats selected!");
                        }
                    }

                    if(tss.getRoom().getseattype(row,col).ordinal() == Seattype.SEAT.ordinal()){
                        tss.getRoom().book(row, col);
                        selectedseattype.add(tss.getRoom().getseattype(row,col));
                        System.out.println("Seat selected!");
                    }

                }else{
                    System.out.println("Sorry the seat is taken. Choose again!");
                    q = q - 1;
                }
            } else {
                System.out.println("Sorry the seat is taken. Choose again!");
                q = q - 1;
            }
        }


        //Ticket create part
        Ticket[] t = new Ticket[Qty];
        ArrayList<Object> priceLists = getPricelistFromFile();
        //String ageprice = priceLists.get(0).toString();
        LinkedHashMap<String,Double> ageList = (LinkedHashMap<String,Double>)priceLists.get(0);
        LinkedHashMap<String,Double> seatTypeList = (LinkedHashMap<String,Double>)priceLists.get(1);
        LinkedHashMap<String,Double> cinemaClassList = (LinkedHashMap<String,Double>)priceLists.get(2);
        LinkedHashMap<String,Double> movieClassList = (LinkedHashMap<String,Double>)priceLists.get(3);
        LinkedHashMap<String,Double> dayList = (LinkedHashMap<String,Double>)priceLists.get(4);

        for(int i=0;i<Qty;i++){
            double ticketprice = 0;
            //Calculating price
            for (Map.Entry<String, Double> age : ageList.entrySet()) {
                String keys = age.getKey();
                Double value = age.getValue();
                //System.out.println(keys+" "+value+" "+ AgeOfMovieGoer.values()[ticketagelist.get(i)].toString());
                if(keys.equals( AgeOfMovieGoer.values()[ticketagelist.get(i)].toString()) ){
                    System.out.println("Calculating for "+AgeOfMovieGoer.values()[ticketagelist.get(i)].toString());
                    ticketprice += value;
                }
            }
            for (Map.Entry<String, Double> seatType : seatTypeList.entrySet()) {
                String keys = seatType.getKey();
                Double value = seatType.getValue();
                if(keys.equals( selectedseattype.get(i).toString() )){
                    System.out.println("Calculating for "+selectedseattype.get(i).toString());
                    ticketprice += value;
                }
            }
            for (Map.Entry<String, Double> cinemaClass : cinemaClassList.entrySet()) {
                String keys = cinemaClass.getKey();
                Double value = cinemaClass.getValue();
                if(keys.equals( tss.getRoom().getCinemaClass().toString() )){
                    System.out.println("Calculating for "+tss.getRoom().getCinemaClass().toString());
                    ticketprice += value;
                }
            }
            for (Map.Entry<String, Double> movieClass : movieClassList.entrySet()) {
                String keys = movieClass.getKey();
                Double value = movieClass.getValue();
                if(keys.equals( movie.getType().toString() )){
                    System.out.println("Calculating for "+movie.getType().toString());
                    ticketprice += value;
                }
            }

            for (Map.Entry<String, Double> day : dayList.entrySet()) {
                String keys = day.getKey();
                Double value = day.getValue();
                //System.out.println(keys+" "+value+" "+ d.toString());
                if(keys.equals( d.toString()) ){
                    System.out.println("Calculating for "+d.toString());
                    ticketprice += value;
                }
            }
            t[i] = new Ticket(1, AgeOfMovieGoer.values()[ticketagelist.get(i)] , movie.getType(), tss.getRoom().getCinemaClass(), d, selectedseattype.get(i), ticketprice);
            //movie.addsales(ticketprice); //add to movie
        }
        //Transaction trans = new Transaction(dtf.format(now).toString() +" "+ transid, t);
        if(isLogined){
            mgName = mg.getName();
            mgMobile = mg.getMobile();
            mgEmail = mg.getEmail();
        }

        if(isGuest){
            System.out.println("Enter your name:");
            mgName = sc.nextLine();
            System.out.println("Enter your mobile:");
            mgMobile = sc.nextInt();
            //clear buffer
            sc.nextLine();
            System.out.println("Enter your email:");
            mgEmail = sc.nextLine();
        }
        Transaction trans = new Transaction(dtf.format(now).toString() +" "+ transid,mgName,mgMobile,mgEmail,t);

        if(isLogined){
            //add transaction to account.txt
                mg.addTransaction(trans);
                //set mg to mgList
                mgList.set(currentMGIndex,mg);
                //set mgList to object.get(1) of account.txt
                accountLists.set(1, mgList);
                //saved object to account.txt
                addAccountListsToFile(accountLists);
        }

        if(isGuest){
            //add transaction to guestTransaction.txt
                //add transaction to transactionList
                transList.add(trans);
                //add transactionList to file
                addTransListToFile(transList);
        }

        //update time in cineplex.txt (local movie list inside cineplex)
            //set timeslot to movie
                int i = 0;
                TimeSlot temp_ts;
                for(i = 0; i < ts1.size(); i++){
                    temp_ts = ts1.get(i);
                    //since the timeslots arraylist is inside movie, which is inside of cineplex
                    //we dont need to check movie and cineplex
                    //and just check stringdate and starttime
                    if(temp_ts.getStringDate().equals(tss.getStringDate()) 
                        && temp_ts.getStartTime().equals(tss.getStartTime())){
                            break;
                        }
                }
            movie.setTimeSlot(i,tss);
            //set cineplex.setmovie
            localMovieIndex = MovieDB.getMovieIndex(movieList, movieTitle);
            cine.setMovie(localMovieIndex,movie);
            //set cineplex to cineplex List
            cathay.set(cineIndex,cine);
            //add cineplex list to file
            cineplexDB.addCineplexListToFile(cathay);

        //update sales in movie.txt (global movie list)
            globalMovieIndex = MovieDB.getMovieIndex(globalMovieList, movieTitle);
            globalMovie = globalMovieList.get(globalMovieIndex);
            //set sales in movie
            globalMovie.addsales(trans.getTotalPrice());
            //set movie to movielist
            globalMovieList.set(globalMovieIndex,globalMovie);
            //add movielist to file
            MovieDB.addMovieListToFile(globalMovieList);

        System.out.println(Qty + " Booking places!");
        System.out.println("Printing out your transaction\n");
        System.out.println("Transaction "+trans.getId());
        trans.printTickets();
        System.out.printf("Total Price SGD %.2f inclusive of GST\n\n",trans.getTotalPrice());
    }


    
}
