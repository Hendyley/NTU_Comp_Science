package MovieGoerModule;

import java.io.Serializable;
import java.util.ArrayList;

public class Cineplex implements Serializable{
    private String name;
    private ArrayList<Cinema> rooms = new ArrayList<Cinema>();
    private ArrayList<Movie> listOfMovies = new ArrayList<Movie>();

    //each room have their own timeslot???
    // private ArrayList<TimeSlot> timeslots = new ArrayList<>();

    public Cineplex(int numRooms, String name) {
        this.name = name;

        // room = new Cinema[numRooms];
        for (int i = 0; i < numRooms; i++) {
            this.rooms.add(new Cinema());
        }
    }

    public Cineplex(String name){
        this.name = name;
    }

    public Cinema getRoom(int i) {
        return this.rooms.get(i);
    }


    public String getName() {
        return this.name;
    }

    // public void addSlot(TimeSlot ts) {

    //     timeslots.add(ts);
    // }

    // public ArrayList<TimeSlot> getTimeslots(){
    //     return timeslots;
    // }

    public ArrayList<Movie> getMovieList() {
        return listOfMovies;
    }

    public ArrayList<Cinema> getRoomList(){
        return rooms;
    }


    public void setRoom(Cinema room) {
        this.rooms.add(room);
    }


    public void addMovie(Movie m){
        this.listOfMovies.add(m);
    }

    public void setMovie(int index, Movie movie) {
        this.listOfMovies.set(index, movie);
    }

    public void removeMovie(int index){
        this.listOfMovies.remove(index);
    }


}
