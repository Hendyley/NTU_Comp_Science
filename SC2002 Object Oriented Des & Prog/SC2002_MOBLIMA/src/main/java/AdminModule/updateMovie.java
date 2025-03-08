package AdminModule;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import MovieGoerModule.AgeOfMovieGoer;
import MovieGoerModule.Cineplex;
import MovieGoerModule.Movie;
import MovieGoerModule.Status;
import MovieGoerModule.TypeOfMovie;

public class updateMovie {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        update();
    }

    public static void update() throws ClassNotFoundException, IOException{
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        String title = "";

        //intention to change movie status across all cineplexes
        ArrayList<Movie> mdbList = MovieDB.getMovieListFromFile();
        MovieDB.printMovies();

        int movieDBIndex = -1;
        while(movieDBIndex == -1){
            System.out.println("Enter title of movie to update: (or 0 to exit)");
            title = sc.nextLine();

            if(title.equals("0")){
                System.out.println("Exiting...");
                return;     //back to settings
            }

            if(MovieDB.isExistingMovie(mdbList, title)){
                System.out.println("Movie is in database");
                movieDBIndex = MovieDB.getMovieIndex(mdbList, title);
            
                //move onto change status
            }
            else{
                System.out.println("Movie does not exist. Try again or enter 0 to exit");
            }
        }
        Movie chosenMovieDB = mdbList.get(movieDBIndex);

        //print out list of cineplex
        // ArrayList<Cineplex> cList = cineplexDB.getCineplexListFromFile();
        // cineplexDB.printCineplexList();

        //select cineplex (enter name and get index)
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

        // Cineplex chosenCineplex = cList.get(cineplexindex);
        // ArrayList<Movie> movieList = chosenCineplex.getMovieList();
        // if(movieList.size() < 1){
        //     System.out.println("No movies in this cineplex");
        //     return;
        // }

        // System.out.println("Movies in cineplex");
        // for(int i = 0; i < movieList.size(); i++){
        //     System.out.println(i+1 + ": " + movieList.get(i).getTitle());
        // }

        // Movie chosenMovie;
        // int movieIndex;
        // while(true){
        //     System.out.println("Enter title of movie to update");
        //     String title = sc.nextLine();
        //     if(cineplexDB.isExistingMovie(movieList, title)){
        //         movieIndex = cineplexDB.getMovieIndex(movieList, title);
        //         chosenMovie = movieList.get(movieIndex);
        //         break;
        //     }
        //     else{
        //         System.out.println("Movie does not exist! Try again");
        //     }
        // }

        System.out.println("original status:");
        chosenMovieDB.printDetails();

        ArrayList<Cineplex> cList = cineplexDB.getCineplexListFromFile();
        int movieIndex = cineplexDB.getMovieIndex(mdbList, title);

        do{
            //change Status, change director, add/delete cast,
            System.out.println("What would you like to change?");
            System.out.println("1: Status");
            System.out.println("2: Type of movie");
            System.out.println("3: Age restriction");
            System.out.println("4: Done");

            choice = sc.nextInt();
                
            switch(choice){
                case 1: //change status
                    System.out.println("Enter the status:");
                    System.out.println("1: COMING_SOON ");
                    System.out.println("2: PREVIEW");
                    System.out.println("3: NOW_SHOWING");
                    System.out.println("4: END_OF_SHOWING");
                    System.out.println("5: BACK");
                    int set = sc.nextInt();
                    switch(set){
                        case 1:
                            chosenMovieDB.setStatus(Status.COMING_SOON);
                            // for(Cineplex c : cList){
                            //     ArrayList<Movie> mList = c.getMovieList();
                            //     for(Movie m : mList){
                            //         if(m.getTitle().equals(title)){
                            //             m.setStatus(Status.COMING_SOON);
                            //         }
                            //     }
                            // }
                            for(Cineplex c : cList){
                                c.getMovieList().get(movieIndex).setStatus(Status.COMING_SOON);
                            }
                            break;
            
                        case 2:
                            chosenMovieDB.setStatus(Status.PREVIEW);
                            for(Cineplex c : cList){
                                c.getMovieList().get(movieIndex).setStatus(Status.PREVIEW);
                            }
                            break;
            
                        case 3:
                            chosenMovieDB.setStatus(Status.NOW_SHOWING);
                            for(Cineplex c : cList){
                                c.getMovieList().get(movieIndex).setStatus(Status.NOW_SHOWING);
                            }
                            break;
            
                        case 4:
                            chosenMovieDB.setStatus(Status.END_OF_SHOWING);
                            for(Cineplex c : cList){
                                c.getMovieList().get(movieIndex).setStatus(Status.END_OF_SHOWING);
                            }
                            break;

                        case 5:
                            System.out.println("Going back...");
                            break;

                        default:
                            System.out.println("Invalid input! Try again!");
                            break;
                    }
                    break;  //break outer case

                case 2: //type of movie
                    System.out.println("Enter the type:");
                    System.out.println("1: REGULAR_2D ");
                    System.out.println("2: REGULAR_3D");
                    System.out.println("3: BLOCKBUSTER_2D");
                    System.out.println("4: BLOCKBUSTER_3D");
                    System.out.println("5: BACK");
                    int type = sc.nextInt();
                    
                    switch(type){
                        case 1:
                            chosenMovieDB.setType(TypeOfMovie.REGULAR_2D);
                            for(Cineplex c : cList)
                                c.getMovieList().get(movieIndex).setType(TypeOfMovie.REGULAR_2D);
                            break;
                        case 2:
                            chosenMovieDB.setType(TypeOfMovie.REGULAR_3D);
                            for(Cineplex c : cList)
                                c.getMovieList().get(movieIndex).setType(TypeOfMovie.REGULAR_3D);
                            break;

                        case 3:
                            chosenMovieDB.setType(TypeOfMovie.BLOCKBUSTER_2D);
                            for(Cineplex c : cList)
                                c.getMovieList().get(movieIndex).setType(TypeOfMovie.BLOCKBUSTER_2D);
                            break;

                        case 4:
                            chosenMovieDB.setType(TypeOfMovie.BLOCKBUSTER_3D);
                            for(Cineplex c : cList)
                                c.getMovieList().get(movieIndex).setType(TypeOfMovie.BLOCKBUSTER_3D);
                            break;

                        case 5:
                            System.out.println("Going back...");
                            break;

                        default:
                            System.out.println("Invalid input! Try again!");
                            break;
                    }

                    break; //break outer case

                case 3: //age restriction
                    System.out.println("Enter the age restriction:");
                    System.out.println("1: ADULT");
                    System.out.println("2: CHILD");
                    System.out.println("3: STUDENT");
                    System.out.println("4: SENIOR");
                    System.out.println("5: BACK");
                    int restriction = sc.nextInt();

                    switch(restriction){
                        case 1:
                            chosenMovieDB.setAgetype(AgeOfMovieGoer.ADULT);
                            for(Cineplex c : cList)
                                c.getMovieList().get(movieIndex).setAgetype(AgeOfMovieGoer.ADULT);
                            break;
                        case 2:
                            chosenMovieDB.setAgetype(AgeOfMovieGoer.CHILD);
                            for(Cineplex c : cList)
                                c.getMovieList().get(movieIndex).setAgetype(AgeOfMovieGoer.CHILD);
                            break;

                        case 3:
                            chosenMovieDB.setAgetype(AgeOfMovieGoer.STUDENT);
                            for(Cineplex c : cList)
                                c.getMovieList().get(movieIndex).setAgetype(AgeOfMovieGoer.STUDENT);
                            break;

                        case 4:
                            chosenMovieDB.setAgetype(AgeOfMovieGoer.SENIOR);
                            for(Cineplex c : cList)
                                c.getMovieList().get(movieIndex).setAgetype(AgeOfMovieGoer.SENIOR);
                            break;

                        case 5:
                            System.out.println("Going back...");
                            break;

                        default:
                            System.out.println("Invalid input! Try again!");
                            break;
                    }

                    break; //break outer case

                case 4:
                    mdbList.set(movieDBIndex, chosenMovieDB);
                    MovieDB.addMovieListToFile(mdbList);
                    // chosenCineplex.setMovie(movieIndex, chosenMovie);
                    // cList.set(cineplexindex, chosenCineplex);
                    cineplexDB.addCineplexListToFile(cList);
                    System.out.println("Done!");
                    System.out.println("Updated details:");
                    chosenMovieDB.printDetails();
                    break;

                default:
                    System.out.println("Invalid input! Try again!");
                    break;
            }

        } while(choice != 4);
    }
}
