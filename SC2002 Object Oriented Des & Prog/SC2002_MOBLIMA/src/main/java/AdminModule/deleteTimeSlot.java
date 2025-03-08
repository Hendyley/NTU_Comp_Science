package AdminModule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import MovieGoerModule.Cineplex;
import MovieGoerModule.Movie;
import MovieGoerModule.TimeSlot;

public class deleteTimeSlot {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        deleteTimeSlot.delete();
    }
    
    public static void delete() throws ClassNotFoundException, IOException{
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

        Cineplex chosenCineplex = cList.get(cineplexIndex);
        ArrayList<Movie> movieList = chosenCineplex.getMovieList();

        //print movielist
        System.out.println("Movies:");
        for(Movie m : movieList){
            System.out.println(m.getTitle());
        }

        //select movie
        int movieIndex;
        String title;
        Movie chosenMovie;
        while(true){
            System.out.println("Enter movie title:");
            title = sc.nextLine();
            if(cineplexDB.isExistingMovie(movieList, title)){
                movieIndex = cineplexDB.getMovieIndex(movieList, title);
                chosenMovie = movieList.get(movieIndex);
                break;
            }
            else if(title.compareTo("0") == 0){
                System.out.println("Going back...");
                return;
            }
            else{
                System.out.println("Movie does not exist. Try again.");
                System.out.println("Or enter 0 to exit");
            }
        }

        //view timeslots for movie
        ArrayList<TimeSlot> movieTS = chosenMovie.getTimeSlots();
        for(int i = 0; i < movieTS.size(); i++){
            System.out.println(i + ": " + movieTS.get(i).getairingtimeformat());
        }

        //select timeslot
        int removeIndex = -1;
        while(true){
            System.out.println("Select TimeSlot number");
            removeIndex = sc.nextInt();
            sc.nextLine();      //clear buffer
            if(removeIndex >= 0 && removeIndex < movieTS.size()){
                break;
            }
            else if(removeIndex == -1){
                System.out.println("Going back...");
                return;
            }
            else{
                System.out.println("Invalid number!");
                System.out.println("Try again, else enter -1 to exit");
            }
        }
        System.out.println("Time slot successfully removed!");
        //remove timeslot
        movieTS.remove(removeIndex);

        chosenCineplex.setMovie(movieIndex, chosenMovie);
        cList.set(cineplexIndex, chosenCineplex);
        cineplexDB.addCineplexListToFile(cList);

        System.out.println("Updated timeslots for movie");
        for(int i = 0; i < movieTS.size(); i++){
            System.out.println(i + ": " + movieTS.get(i).getairingtimeformat());
        }


    }
}
