package MovieGoerModule;

import java.io.Serializable;

public class Ticket implements Serializable{
    private int quantity;
    private AgeOfMovieGoer agetype;
    private TypeOfMovie movieType;
    private double price;
    private ClassOfCinema cinemaClass;

    private Seattype seattype;
    private Day day;


    public Ticket() {

    }

    public Ticket(int quantity, AgeOfMovieGoer age, TypeOfMovie movieType, ClassOfCinema cinemaClass, Day day,Seattype seattype,double price) {
        this.quantity = quantity;
        this.agetype = age;
        this.movieType = movieType;
        this.cinemaClass = cinemaClass;
        this.day = day;
        this.seattype = seattype;
        this.price = price;
    }

    public double calcPrice() {

        // switch case give the output of usual adult prices with diff days;
        switch (day) {
            case REMAINING_DAYS:
                if (movieType == TypeOfMovie.REGULAR_2D) {
                    price = 11;
                }

                if (movieType == TypeOfMovie.REGULAR_3D) {
                    price = 15;
                }

                if (movieType == TypeOfMovie.BLOCKBUSTER_2D) {
                    price = 15;
                }

                if (movieType == TypeOfMovie.BLOCKBUSTER_3D) {
                    price = 20;
                }
                break;
            case MON_TO_WED:
                if (movieType == TypeOfMovie.REGULAR_2D) {
                    price = 8.5;
                }

                if (movieType == TypeOfMovie.REGULAR_3D) {
                    price = 11;
                }

                if (movieType == TypeOfMovie.BLOCKBUSTER_2D) {
                    price = 11;
                }

                if (movieType == TypeOfMovie.BLOCKBUSTER_3D) {
                    price = 16;
                }
                break;

            case THURS:
                if (movieType == TypeOfMovie.REGULAR_2D) {
                    price = 9.5;
                }

                if (movieType == TypeOfMovie.REGULAR_3D) {
                    price = 11;
                }

                if (movieType == TypeOfMovie.BLOCKBUSTER_2D) {
                    price = 11;
                }

                if (movieType == TypeOfMovie.BLOCKBUSTER_3D) {
                    price = 16;
                }
                break;

            case FRI_BEFORE_6:
                if (movieType == TypeOfMovie.REGULAR_2D) {
                    price = 9.5;
                }

                if (movieType == TypeOfMovie.REGULAR_3D) {
                    price = 15;
                }

                if (movieType == TypeOfMovie.BLOCKBUSTER_2D) {
                    price = 15;
                }

                if (movieType == TypeOfMovie.BLOCKBUSTER_3D) {
                    price = 20;
                }

            default:
                price = 11;
        }

        // student prices
        if (agetype == AgeOfMovieGoer.STUDENT && day != Day.REMAINING_DAYS) {
            if (movieType == TypeOfMovie.REGULAR_2D) {
                price = 7;
            }

            if (movieType == TypeOfMovie.REGULAR_3D) {
                price = 9;
            }

            if (movieType == TypeOfMovie.BLOCKBUSTER_2D) {
                price = 9;
            }

            if (movieType == TypeOfMovie.BLOCKBUSTER_3D) {
                price = 11;
            }
        }

        // SENIOR prices
        if (agetype == AgeOfMovieGoer.SENIOR && day != Day.REMAINING_DAYS) {
            if (movieType == TypeOfMovie.REGULAR_2D) {
                price = 4;
            }
        }

        // CHILD prices
        if (agetype == AgeOfMovieGoer.STUDENT && day != Day.REMAINING_DAYS) {
            if (movieType == TypeOfMovie.REGULAR_2D) {
                price = 5;
            }

            if (movieType == TypeOfMovie.REGULAR_3D) {
                price = 7;
            }

            if (movieType == TypeOfMovie.BLOCKBUSTER_2D) {
                price = 7;
            }

            if (movieType == TypeOfMovie.BLOCKBUSTER_3D) {
                price = 7;
            }
        }

        // just add final constant price value of cinema class
        if (cinemaClass == ClassOfCinema.PLATINUM) {
            price += 10;
        }

        if (cinemaClass == ClassOfCinema.DOLBY) {
            price += 5;
        }

        return price * quantity;
    }

    public double getPrice() {
        return price;
    }
    public AgeOfMovieGoer get(){
        return agetype;
    }

    public TypeOfMovie getMovieType(){
        return movieType;
    }

    public Day getDay(){
        return day;
    }
    public ClassOfCinema getCinemaClass(){
        return cinemaClass;
    }

    public int getQuantity(){
        return quantity;
    }

    public AgeOfMovieGoer getAgetype(){
        return agetype;
    }

    public  Seattype getSeattype(){
        return seattype;
    }

}
