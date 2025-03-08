package AdminModule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import MovieGoerModule.Cinema;
import MovieGoerModule.Cineplex;
import MovieGoerModule.ClassOfCinema;
import MovieGoerModule.Movie;
import MovieGoerModule.Status;
import MovieGoerModule.TimeSlot;

public class addTimeslot {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        addTimeslot.addTS();
    }
    
    public static void addTS() throws ClassNotFoundException, IOException{
        Scanner sc = new Scanner(System.in);

        //print out list of cineplex
        ArrayList<Cineplex> cList = cineplexDB.getCineplexListFromFile();
        //cineplexDB.printCineplexList();

        //select cineplex (enter name and get index)
        for(int i = 0; i < cList.size(); i++){
            System.out.println(i + ": " + cList.get(i).getName());
        }

        int cineplexIndex = -1;
        while(true){
            System.out.println("Enter the index of cineplex: ");
            cineplexIndex = sc.nextInt();
            sc.nextLine(); //clear buffer
            if(cineplexIndex >= cList.size() || cineplexIndex < 0){
                System.out.println("Invalid index! Try again");
            }
            else break;
        }

        // int cineplexindex;
        // while(true){
        //     System.out.println("Enter the name of cineplex:");
        //     String nameCineplex = sc.nextLine();
        //     if(cineplexDB.isExistingCineplex(cList, nameCineplex)){
        //         cineplexindex = cineplexDB.getCineplexIndex(cList, nameCineplex);
        //         break;
        //     }
        //     else{
        //         System.out.println("Cineplex does not exist! Try again!");
        //     }
        // }

        //enter a date
        System.out.println("Enter the date for showtime (DD/MM/YYYY):");
        String date;
        while(true){
            date = sc.nextLine();
            if(!dateChecker.check(date)){
                System.out.println("Invalid date. Try again.");
            }
            else{
                break;
            }
        }

        //get whole movie list
        Cineplex chosenCineplex = cList.get(cineplexIndex);
        ArrayList<Movie> movieList = chosenCineplex.getMovieList();

        //create timeslot array
        ArrayList<TimeSlot> timeslot_day = new ArrayList<TimeSlot>();

        //get timeslots for same date
        //from movieList get showtimes for movies on the same date

        //looping through entire movieList
        for(int i = 0; i < movieList.size(); i++){
            ArrayList<TimeSlot> temp = movieList.get(i).getTimeSlots();

            //looping through timeslots for movies
            for(int j = 0; j < temp.size(); j++){
                String tempDate = temp.get(j).getStringDate();

                //if same date add into time slot array
                if(date.compareTo(tempDate) == 0){
                    timeslot_day.add(temp.get(j));
                }
            }
        }

        //print timeslot_day
        //printTimeSlot.printTS(timeslot_day);

        //loop movieList, print NOW_SHOWING
        System.out.println("Movies NOW_SHOWING/PREVIEW :");
        for(int i = 0; i < movieList.size(); i++){
            Movie m = movieList.get(i);
            if(m.getStatus() == Status.NOW_SHOWING || m.getStatus() == Status.PREVIEW){
                System.out.println(m.getTitle());
            }
        }

        //staff select movie (check if movie exists)
        int movieIndex;
        String title;
        Movie chosenMovie;
        //ArrayList<Movie> movieDBList = MovieDB.getMovieListFromFile();
        while(true){
            System.out.println("Enter movie title:");
            title = sc.nextLine();
            if(cineplexDB.isExistingMovie(movieList, title)){
                System.out.println("Movie in cineplex");
                movieIndex = cineplexDB.getMovieIndex(movieList, title);
                chosenMovie = movieList.get(movieIndex);
                //check for status PREVIEW/NOW SHOWING
                //if not do not allow addingtimeslot
                Status status = chosenMovie.getStatus();
                if(status == Status.END_OF_SHOWING || status == Status.COMING_SOON){
                    System.out.println("Status of movie: " + status);
                    System.out.println("Change the status of movie to PREVIEW/NOW_SHOWING to add time slots");
                    System.out.println("Exiting add time slot...");
                }
                break;
            }
            // else if(MovieDB.isExistingMovie(movieDBList, title)){
            //     System.out.println("Movie in database");
            //     int index = MovieDB.getMovieIndex(movieDBList, title);
            //     chosenMovie = movieDBList.get(index);
            // }
            else{
                System.out.println("Movie does not exist. Try again.");
            }
        }

        //print cinema list (should roomID be integer or string)
        ArrayList<String> idList = new ArrayList<String>();
        ArrayList<Cinema> roomList = chosenCineplex.getRoomList();
        System.out.println("ID of rooms");
        for(int i = 0; i < roomList.size(); i++){
            String roomID = roomList.get(i).getID();
            System.out.println("Room "+ (i+1) + ": " + roomID);
            idList.add(roomID);
        }

        //staff enter roomID
        String chosenRoomID;
        ClassOfCinema chosenRoomClass;
        boolean flag = false;
        Cinema chosenRoom = new Cinema();
        while(true){
            System.out.println("Choose a room:");
            chosenRoomID = sc.nextLine();
            for(int i = 0; i < roomList.size(); i++){
                if(roomList.get(i).getID().compareTo(chosenRoomID) == 0){
                    chosenRoomClass = roomList.get(i).getCinemaClass();
                    chosenRoom = new Cinema(chosenRoomID,chosenRoomClass);
                    flag = true;
                }
            }
            
            if(flag) break; //break while loop when chosen room is correct
            else{
                System.out.println("Room does not exist...");   
            }
        }

        //get timeslots in the same room
        ArrayList<TimeSlot> roomTS = new ArrayList<TimeSlot>();
        for(TimeSlot ts : timeslot_day){
            if(ts.getRoom().getID().equals(chosenRoomID)){
                roomTS.add(ts);
            }
        }

        //print timeslots
        //input start time
        String startTime;
        String endTime;
        boolean clash = false;
        do{
            while(true){
                clash = false;
                System.out.println("Time slots:");
                for(TimeSlot ts : roomTS){
                    System.out.println(ts.getairingtimeformat());
                }

                System.out.println("Enter start time:");
                startTime = sc.nextLine();

                //if user wants to exit
                if(startTime.compareTo("0") == 0){
                    System.out.println("Exiting from adding timeslot...");
                    return; //back to configure settings
                }
                //check if time entered is valid format
                else if(timeChecker.isValidTime(startTime)){
                    endTime = TimeSlot.calculateEndTime(startTime, chosenMovie.getMovieDurationMin());
                    break;  //break while
                }
                else{
                    System.out.println("Invalid time. Try again");
                }
            }

            int startChosen = Integer.parseInt(startTime);  //start time input
            int endChosen = Integer.parseInt(endTime);

            for(TimeSlot ts : roomTS){
                int end = Integer.parseInt(ts.getEndTime());
                int start = Integer.parseInt(ts.getStartTime());
                //check for clash
                if((startChosen >= start && startChosen <= end) || (endChosen >= start && endChosen <= end)
                    || (startChosen <= start && endChosen >= end)){
                    clash = true;
                    System.out.println("TIME SLOT CLASH");
                    System.out.println("Enter another timeslot or 0 to exit");
                }
            }

        } while(clash);
        
        System.out.println("Time slot successfully added!");

        //cases for clashes
        //                  start----end
        //       start-------------------------------end
        //start---------------end
        //                          start---------------------end
        //start-----------------------------------------------end

        TimeSlot toAdd = new TimeSlot(date, startTime, chosenRoom, title,
                chosenMovie.getMovieDurationMin(), chosenMovie.getType());
        chosenMovie.addTimeSlot(toAdd);  //create set timeslot setTimeSlot(TimeSlot ts)
        chosenCineplex.setMovie(movieIndex, chosenMovie);     //add back movie w updated ts
        cList.set(cineplexIndex, chosenCineplex);      //add back updated cineplex
        cineplexDB.addCineplexListToFile(cList);    //write back to file

        //print to check
        System.out.println("Updated timeslots for movie");
        for(TimeSlot ts : chosenMovie.getTimeSlots()){
            System.out.println(ts.getairingtimeformat());
        }

    }

}
