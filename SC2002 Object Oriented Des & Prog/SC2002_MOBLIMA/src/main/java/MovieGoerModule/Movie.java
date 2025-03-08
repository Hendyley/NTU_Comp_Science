package MovieGoerModule;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Movie implements Serializable{
    private String title;
    private Status status = Status.NOW_SHOWING;

    //Agetype?
    private AgeOfMovieGoer age_restriction = AgeOfMovieGoer.ADULT;
    private String synopsis = "NA";
    private String director = "NA";
    private ArrayList<String> cast = new ArrayList<>();
    private int movieDurationMin = 30;
    private double sales = 0;
    private float rating = -1;
    private TypeOfMovie type = TypeOfMovie.BLOCKBUSTER_3D;
    private ArrayList<Review> allreviews = new ArrayList<>();
    private ArrayList<TimeSlot> timeslots = new ArrayList<>();

    private static final DecimalFormat df = new DecimalFormat("0.0");

    public Movie(String title ) {
        this.title = title;
    }

    public Movie(String title,String director, ArrayList<String> cast,String synopsis
        ,Status status, TypeOfMovie type, int movieDurationMin, AgeOfMovieGoer age_restriction){
        this.title = title;
        this.director = director;
        this.cast = cast;
        this.synopsis = synopsis;
        this.status = status;
        this.type = type;
        this.movieDurationMin = movieDurationMin;
        this.age_restriction = age_restriction;
    }

    public void addTimeSlot(TimeSlot ts) {
        this.timeslots.add(ts);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(Status stat) {
        this.status = stat;
    }

    public void setSynopsis(String synop) {
        this.synopsis = synop;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void addCast(String name) {
        cast.add(name);
    }

    public void clearCast() {
        cast.clear();
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
    
    public void setType(TypeOfMovie type){
        this.type = type;
    }

    public TypeOfMovie getType(){
        return type;
    }

    public void addsales(double ticketsales){
         sales = sales + ticketsales;
    }

    public AgeOfMovieGoer getAge_restriction(){
        return age_restriction;
    }
    public void setAgetype(AgeOfMovieGoer agetype){
        this.age_restriction = agetype;
    }

    public void updatereviewscore(ArrayList<Review> allreviews){
        int rate=0;
        for(int i=0;i<allreviews.size();i++){
            rate = rate + allreviews.get(i).getUserRating();
        }
        rating = (float) rate / allreviews.size();
        //return rating;
    }
    
    public void setTimeSlot(int index, TimeSlot ts){
        this.timeslots.set(index, ts);
    }

    public String getRating(){

        if(allreviews.size()>=1){
            return df.format(rating);
        }else {
            return "NA";
        }
    }

    public double getrealrating(){
        return rating;
    }

    public int getnumberofreviewer(){
        return allreviews.size();
    }


    public double getSales(){
        return sales;
    }

    public ArrayList<Review> getreviewlist(){
        return allreviews;
    }

    public ArrayList<TimeSlot> getTimeSlots(){
        return timeslots;
    }


    public int getMovieDurationMin(){
        return movieDurationMin;
    }


    public String getDirector(){
        return director;
    }

    public void addReview(Review review) {
        allreviews.add(review);
    }

    public void addSlot(TimeSlot slot) {
        for (int i = 0; i < timeslots.size(); i++) {
            // if (slot.getStringDate().compareTo(director))
        }

        timeslots.add(slot);
    }

    public String getTitle() {
        return title;
    }

    public String getSynopsis(){
        return synopsis;
    }

    public Status getStatus(){
        return status;
    }

    public void printDetails() {
        System.out.println("Movie title: " + title);
        System.out.println("Status: " + status);
        System.out.println("Synopsis: " + synopsis);
        System.out.println("Director: " + director);
        System.out.println("Cast: ");
        for (int i = 0; i < cast.size()-1; i++){
            System.out.print(cast.get(i) + ", ");
        }
        System.out.println(cast.get(cast.size()-1));
        System.out.println("Movie Type: " +type);
        System.out.println("Rating: " + rating);
    }
}
