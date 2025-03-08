package AdminModule;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import MovieGoerModule.Cineplex;
import MovieGoerModule.Movie;

public class deleteMovie {
    //remove movie by changing status to end of showing 
    public static void delete() throws ClassNotFoundException, IOException{
        //delete movie from database?
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        ArrayList<Movie> movieList;
        int index = -1;

        System.out.println("Enter the title to delete:");
        String title = sc.nextLine();
        movieList = MovieDB.getMovieListFromFile();
        index = MovieDB.getMovieIndex(movieList, title);        //return -1 if does not exist
        if(index < 0){ 
            System.out.println("Movie does not exist");
            return;
        }

        Movie m = movieList.get(index);
        System.out.println("movie details:");
        m.printDetails();


        ArrayList<Cineplex> cineplexList = cineplexDB.getCineplexListFromFile();
        Cineplex cine;
        ArrayList<Movie> cineMovieList;
        int cineMovieIndex = -1;
        //for loop cineplexlist from cineplex.txt
        for(int i = 0; i < cineplexList.size(); i++){
            //get each cineplex
            cine = cineplexList.get(i);
            //get cineplex movielist
            cineMovieList = cine.getMovieList();
            //find movie using movie name and get index of movie
            cineMovieIndex = cineplexDB.getMovieIndex(cineMovieList, title);

            if(cineMovieIndex != -1){
                // remove movie from current cineplex
                cine.removeMovie(cineMovieIndex);
                //set current cineplex into cineplexlist
                cineplexList.set(i,cine);
            }

        }
            //saved updated cineplex list into cineplex.txt
            cineplexDB.addCineplexListToFile(cineplexList);


        do{
            System.out.println("Confirm Delete?");
            System.out.println("1: YES ");
            System.out.println("2: NO ");
            choice = sc.nextInt();

            switch(choice){
                case 1:
                    movieList.remove(index);
                    MovieDB.addMovieListToFile(movieList);
                    System.out.println("Movie successfully deleted!");
                    break;
                case 2:
                    System.out.println("Delete have been cancelled");
                    break;
                default:
                    break;
            }

        }while( !(choice == 1 || choice == 2));
        MovieDB.printMovieList();
    }
}
