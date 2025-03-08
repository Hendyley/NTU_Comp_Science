package AdminModule;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import MovieGoerModule.AgeOfMovieGoer;
import MovieGoerModule.Movie;
import MovieGoerModule.Status;
import MovieGoerModule.TypeOfMovie;

public class MovieDB {
    private final static String MOVIE_FILE_NAME = "Movie.txt";

    public static ArrayList<Movie> getMovieListFromFile() throws IOException, ClassNotFoundException{
        ArrayList<Movie> movieList = new ArrayList<Movie>();

        try{
            FileInputStream fileInputStream2 = new FileInputStream(MOVIE_FILE_NAME);
            ObjectInputStream objectInputStream2 = new ObjectInputStream(fileInputStream2);
            movieList = (ArrayList<Movie>) objectInputStream2.readObject();
            objectInputStream2.close();
            System.out.println("Movie List retrieved from File");           
        }catch(FileNotFoundException e){
            FileOutputStream fileOutputStream = new FileOutputStream(MOVIE_FILE_NAME);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            movieList = generateDummyData();
            objectOutputStream.writeObject(movieList);
            objectOutputStream.flush();
            objectOutputStream.close();
            System.out.println("Movie List File not found, creating new Movie List File");           
        }

        return movieList;
    }

    public static void addMovieListToFile(ArrayList<Movie> movieList) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(MOVIE_FILE_NAME);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(movieList);
        objectOutputStream.flush();
        objectOutputStream.close();
        System.out.printf("Movie Lists added to File\n");
    }

    public static boolean isExistingMovie(ArrayList<Movie> movieList, String newMovieTitle){
        for(Movie m: movieList){
            if(m.getTitle().equals(newMovieTitle)){
                return true;
            }
        }
        return false;
    }

    public static int getMovieIndex(ArrayList<Movie> movieList, String title){
        String currentTitle = "";
        for(int i = 0; i < movieList.size(); i++){
           currentTitle = movieList.get(i).getTitle();
           if(currentTitle.equals(title)){
                return i;
           };
        }
        return -1;
    }

    public static void printMovieList() throws ClassNotFoundException, IOException{
        ArrayList<Movie> movieList = getMovieListFromFile();

        System.out.println();
        System.out.println("Printing out Movie List");
        for(Movie m : movieList){
            m.printDetails();
            System.out.printf("Ticket Sales: %.2f, \n\n\n", m.getSales());
        }
    }

    public static void printMovies() throws ClassNotFoundException, IOException{
        ArrayList<Movie> movieList = getMovieListFromFile();

        System.out.println();
        System.out.println("Printing out Movie List");
        for(int i = 0; i < movieList.size(); i++){
            System.out.println(i+1 + ": " + movieList.get(i).getTitle());
        }

    }


    private static ArrayList<Movie> generateDummyData(){
        ArrayList<Movie> movieList = new ArrayList<>();
        Movie movie1,movie2,movie3,movie4;
        String movieTitle;
        String director;
        ArrayList<String> cast;
        String synopsis;
        int movieDurationMin;
    
        //movie1
        movieTitle = "ONE PIECE FILM RED";
        director ="Goro Taniguchi";
        cast = new ArrayList<String>(Arrays.asList("Ikue Otani","Kaori Nazuka","Mayumi Tanaka"));
        synopsis = "Uta - the most beloved singer in the world.\n"
        +"Her voice, which she sings with while concealing her true identity\n";
        movieDurationMin = 115;
        movie1 = new Movie(movieTitle,director,cast,synopsis,Status.NOW_SHOWING
            ,TypeOfMovie.BLOCKBUSTER_2D,movieDurationMin,AgeOfMovieGoer.STUDENT);
        //movie2
        movieTitle = "Kung Fu Hustle";
        director ="Stephen Chow";
        cast = new ArrayList<String>(Arrays.asList("Stephen Chow","Danny Chan","Yuen Wah"));
        synopsis = "When the hapless Sing (Stephen Chow) and his dim-witted pal,\n"
         +"Bone (Feng Xiaogang), try to scam the residents of Pig Sty Alley\n";
        movieDurationMin = 98;
        movie2 = new Movie(movieTitle,director,cast,synopsis,Status.COMING_SOON
            ,TypeOfMovie.BLOCKBUSTER_2D, movieDurationMin,AgeOfMovieGoer.STUDENT);


        //movie3
        movieTitle = "Black Adam";
        director ="Jaume Collet-Serra";
        cast = new ArrayList<String>(Arrays.asList("The Rock","Sam Worthington"));
        synopsis = "In 2600 BC, the tyrannical king Ahk-Ton of Kahndaq creates the Crown of Sabbac to attain great power. After attempting to stage a revolt, a young slave boy is given the powers of Shazam by the Council of Wizards, transforming him into Kahndaq's heroic champion, who kills Ahk-Ton and ends his reign.\n";
        movieDurationMin = 125;
        movie3 = new Movie(movieTitle,director,cast,synopsis,Status.NOW_SHOWING
                ,TypeOfMovie.BLOCKBUSTER_3D,movieDurationMin,AgeOfMovieGoer.ADULT);

        //movie4
        movieTitle = "Avatar: The Way of Water";
        director ="James Cameron";
        cast = new ArrayList<String>(Arrays.asList("Zoe Saldana","Sam Worthington"));
        synopsis = "the second film in Cameron's Avatar franchise";
        movieDurationMin = 190;
        movie4 = new Movie(movieTitle,director,cast,synopsis,Status.PREVIEW
                ,TypeOfMovie.REGULAR_2D,movieDurationMin,AgeOfMovieGoer.CHILD);
        

        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);
        movieList.add(movie4);
        return movieList;
    }
}
