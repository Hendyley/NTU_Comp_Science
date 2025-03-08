package AdminModule;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import MovieGoerModule.AgeOfMovieGoer;
import MovieGoerModule.Cinema;
import MovieGoerModule.Cineplex;
import MovieGoerModule.ClassOfCinema;
import MovieGoerModule.Movie;
import MovieGoerModule.Status;
import MovieGoerModule.TimeSlot;
import MovieGoerModule.TypeOfMovie;

public class cineplexDB {
    private final static String Cineplex_FILE_NAME = "Cineplex.txt";

    public static ArrayList<Cineplex> getCineplexListFromFile() throws IOException, ClassNotFoundException{
        ArrayList<Cineplex> cineplexList;

        try{
            FileInputStream fileInputStream2 = new FileInputStream(Cineplex_FILE_NAME);
            ObjectInputStream objectInputStream2 = new ObjectInputStream(fileInputStream2);
            cineplexList = (ArrayList<Cineplex>) objectInputStream2.readObject();
            objectInputStream2.close();
            System.out.println("Cineplex List retrieved from File");           
        }catch(FileNotFoundException e){
            FileOutputStream fileOutputStream = new FileOutputStream(Cineplex_FILE_NAME);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            cineplexList = generateDummyData();
            objectOutputStream.writeObject(cineplexList);
            objectOutputStream.flush();
            objectOutputStream.close();
            System.out.println("Cineplex List File not found, creating new Cineplex List File");           
        }

        return cineplexList;
    }

    public static void addCineplexListToFile(ArrayList<Cineplex> CineplexList) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(Cineplex_FILE_NAME);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(CineplexList);
        objectOutputStream.flush();
        objectOutputStream.close();
        System.out.printf("Cineplex Lists added to File\n");
    }

    public static boolean isExistingCineplex(ArrayList<Cineplex> cineplexList, String newCineplexnameCineplex){
        for(Cineplex cine: cineplexList){
            if(cine.getName().equals(newCineplexnameCineplex)){
                return true;
            }
        }
        return false;
    }

    public static int getCineplexIndex(ArrayList<Cineplex> cineplexList, String nameCineplex){
        String currentnameCineplex = "";
        for(int i = 0; i < cineplexList.size(); i++){
           currentnameCineplex = cineplexList.get(i).getName();
           if(currentnameCineplex.equals(nameCineplex)){
                return i;
           };
        }
        return -1;
    }

    public static void printCineplexList() throws ClassNotFoundException, IOException{
        ArrayList<Cineplex> cineplexList = getCineplexListFromFile();

        System.out.println();
        System.out.println("Printing out Cineplex List");
        for(Cineplex cineplex : cineplexList){
            System.out.println(cineplex.getName());
            System.out.println("-----------------");
            for(Cinema room : cineplex.getRoomList()){
                System.out.printf("ID: %s\n",room.getID());
            }
            System.out.println("");
        }
        System.out.printf("\n\n");

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
    
    public static ArrayList<Cineplex> generateDummyData(){
        ArrayList<Cineplex> cineplexList = new ArrayList<>();
        Cinema cinema1,cinema2,cinema3, tempCinema;
        Movie movie1,movie2,movie3,movie4;
        String movieTitle;
        String director;
        ArrayList<String> cast;
        String synopsis;
        int movieDurationMin;
        TimeSlot timeslot1,timeslot2,timeslot3,timeslot4,timeslot5,timeslot6;
        

        ///////////////////////////// Cineplex 1
        Cineplex cineplex1 = new Cineplex("Cathay AMK Hub");
        cinema1 = new Cinema("AMK_001",ClassOfCinema.REGULAR);
        cinema2 = new Cinema("AMK_002",ClassOfCinema.DOLBY);
        cinema3 = new Cinema("AMK_003",ClassOfCinema.PLATINUM);
        //generate special seat
        cineplex1.setRoom(cinema1);
        cineplex1.setRoom(cinema2);
        cineplex1.setRoom(cinema3);
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
                
        //timeslot1
        tempCinema =  new Cinema("AMK_001",ClassOfCinema.REGULAR);
        timeslot1 = new TimeSlot("18/11/2022","1600",tempCinema
            ,movie1.getTitle(),movie1.getMovieDurationMin(),movie1.getType());

        //timeslot2
        tempCinema = new Cinema("AMK_002",ClassOfCinema.DOLBY);
        timeslot2 = new TimeSlot("18/12/2022","1900",tempCinema
            ,movie2.getTitle(),movie2.getMovieDurationMin(),movie2.getType());


        //timeslot3
        tempCinema = new Cinema("AMK_003",ClassOfCinema.PLATINUM);
        timeslot3 = new TimeSlot("22/11/2022","1500",tempCinema
            ,movie3.getTitle(),movie3.getMovieDurationMin(),movie3.getType());

        //timeslot4
        tempCinema = new Cinema("AMK_001",ClassOfCinema.REGULAR);
        timeslot4 = new TimeSlot("14/11/2022","2000",tempCinema
            ,movie4.getTitle(),movie4.getMovieDurationMin(),movie4.getType());

        //timeslot5
        tempCinema =  new Cinema("AMK_001",ClassOfCinema.REGULAR);
        timeslot5 = new TimeSlot("18/11/2022","1200",tempCinema
                ,movie1.getTitle(),movie1.getMovieDurationMin(),movie1.getType());

        //timeslot6
        tempCinema =  new Cinema("AMK_001",ClassOfCinema.REGULAR);
        timeslot6 = new TimeSlot("12/11/2022","1200",tempCinema
                ,movie1.getTitle(),movie1.getMovieDurationMin(),movie1.getType());

        //settings
        movie1.addSlot(timeslot1);
        movie2.addSlot(timeslot2);
        movie3.addSlot(timeslot3);
        movie4.addSlot(timeslot4);
        movie1.addSlot(timeslot5);
        movie1.addSlot(timeslot6);
        cineplex1.addMovie(movie1);
        cineplex1.addMovie(movie2);
        cineplex1.addMovie(movie3);
        cineplex1.addMovie(movie4);
        
        /////////////////////// Cineplex 2
        Cineplex cineplex2 = new Cineplex("Cathay Parkway Parade");
        cinema1 = new Cinema("PP_001",ClassOfCinema.REGULAR);
        cinema2 = new Cinema("PP_002",ClassOfCinema.DOLBY);
        cinema3 = new Cinema("PP_003",ClassOfCinema.PLATINUM);

        cineplex2.setRoom(cinema1);
        cineplex2.setRoom(cinema2);
        cineplex2.setRoom(cinema3);

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

        cineplex2.addMovie(movie1);
        cineplex2.addMovie(movie2);
        cineplex2.addMovie(movie3);
        cineplex2.addMovie(movie4);

        /////////////////////// Cineplex 3
        Cineplex cineplex3 = new Cineplex("Cathay West Mall");
        cinema1 = new Cinema("WM_001",ClassOfCinema.REGULAR);
        cinema2 = new Cinema("WM_002",ClassOfCinema.DOLBY);
        cinema3 = new Cinema("WM_003",ClassOfCinema.PLATINUM);

        cineplex3.setRoom(cinema1);
        cineplex3.setRoom(cinema2);
        cineplex3.setRoom(cinema3);
        
        //movie1
        movieTitle = "ONE PIECE FILM RED";
        director ="Goro Taniguchi";
        cast = new ArrayList<String>(Arrays.asList("Ikue Otani","Kaori Nazuka","Mayumi Tanaka"));
        synopsis = "Uta - the most beloved singer in the world.\n"
        +"Her voice, which she sings with while concealing her true identity";
        movieDurationMin = 115;
        movie1 = new Movie(movieTitle,director,cast,synopsis,Status.NOW_SHOWING
            ,TypeOfMovie.BLOCKBUSTER_2D,movieDurationMin,AgeOfMovieGoer.STUDENT);
        //movie2
        movieTitle = "Kung Fu Hustle";
        director ="Stephen Chow";
        cast = new ArrayList<String>(Arrays.asList("Stephen Chow","Danny Chan","Yuen Wah"));
        synopsis = "When the hapless Sing (Stephen Chow) and his dim-witted pal,\n"
            +"Bone (Feng Xiaogang), try to scam the residents of Pig Sty Alley";
        movieDurationMin = 98;
        movie2 = new Movie(movieTitle,director,cast,synopsis,Status.COMING_SOON
            ,TypeOfMovie.BLOCKBUSTER_2D, movieDurationMin,AgeOfMovieGoer.STUDENT);

        //movie3
        movieTitle = "Black Adam";
        director ="Jaume Collet-Serra";
        cast = new ArrayList<String>(Arrays.asList("The Rock","Sam Worthington"));
        synopsis = "In 2600 BC, the tyrannical king Ahk-Ton of Kahndaq creates the Crown of Sabbac to attain great power. After attempting to stage a revolt, a young slave boy is given the powers of Shazam by the Council of Wizards, transforming him into Kahndaq's heroic champion, who kills Ahk-Ton and ends his reign.";
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

        cineplex3.addMovie(movie1);
        cineplex3.addMovie(movie2);
        cineplex3.addMovie(movie3);
        cineplex3.addMovie(movie4);

        ///////////////////// adding to cineplexlist
        cineplexList.add(cineplex1);
        cineplexList.add(cineplex2);
        cineplexList.add(cineplex3);
        
        return cineplexList;
    }

}
