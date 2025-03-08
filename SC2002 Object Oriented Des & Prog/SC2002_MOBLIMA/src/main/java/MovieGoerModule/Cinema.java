package MovieGoerModule;

import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Serializable{
    final int ROW = 10;
    final int COL = 12;
    private Seat[][] seats;
    private String id;
    private ClassOfCinema cinemaClass;


    // set a default layout
    public Cinema() {
        seats = new Seat[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                seats[i][j] = new Seat();
                seats[i][j].setSeatt(Seattype.SEAT);
            }
        }

        setseattype(9,0,Seattype.COUPLE_SEAT);
        setseattype(9,2,Seattype.COUPLE_SEAT);
        setseattype(9,4,Seattype.COUPLE_SEAT);
        setseattype(9,6,Seattype.COUPLE_SEAT);

        setseattype(8,0,Seattype.ELITE_SEAT);
        setseattype(8,2,Seattype.ELITE_SEAT);

        setseattype(8,4,Seattype.ULTIMA_SEAT);
        setseattype(8,6,Seattype.ULTIMA_SEAT);
    }

    // if different cinemas have different layouts?
    // public Cinema(int rows, int columns) {
    //     seats = new Seat[rows][columns];
    //     for (int i = 0; i < rows; i++) {
    //         for (int j = 0; j < columns; j++) {
    //             seats[i][j] = new Seat();
    //         }
    //     }
    // }
    public Cinema(String id, ClassOfCinema cinemaClass) {
        this.id = id;
        this.cinemaClass = cinemaClass;
        seats = new Seat[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                seats[i][j] = new Seat();
                seats[i][j].setSeatt(Seattype.SEAT);
            }
        }

        setseattype(9,0,Seattype.COUPLE_SEAT);
        setseattype(9,2,Seattype.COUPLE_SEAT);
        setseattype(9,4,Seattype.COUPLE_SEAT);
        setseattype(9,6,Seattype.COUPLE_SEAT);

        setseattype(8,0,Seattype.ELITE_SEAT);
        setseattype(8,2,Seattype.ELITE_SEAT);

        setseattype(8,4,Seattype.ULTIMA_SEAT);
        setseattype(8,6,Seattype.ULTIMA_SEAT);

    }

    public void printSeats() { // maybe a separate class?
        System.out.println("   1  2  3  4  5  6  7  8  9  10 11 12");
        char alpha = 'A';
        for (int i = 0; i < seats.length; i++) {
            System.out.print(alpha + " ");
            for (int j = 0; j < seats[0].length; j++) {
                if (seats[i][j].isTaken()) {
                    System.out.print("[X]");
                } else {
                    System.out.print("[ ]");
                }
            }
            System.out.println(" " + alpha);
            alpha++;
        }
    }

    public void book(int row, int col) {

        if(seats[row][col].getSeatt().ordinal() != Seattype.SEAT.ordinal()){
            seats[row][col].bookSeat();
            if(col %2 !=0){
                seats[row][col-1].bookSeat();
            }else{
                seats[row][col+1].bookSeat();
            }
        }else {
            seats[row][col].bookSeat();
        }

    }

    public boolean checkseat(int row, int col){

        return  seats[row][col].isTaken();
    }

    public void setseattype(int row, int col,Seattype seattype){
        seats[row][col].setSeatt(seattype);
        if(col %2 !=0){
            seats[row][col-1].setSeatt(seattype);
        }else{
            seats[row][col+1].setSeatt(seattype);
        }
    }

    public Seattype getseattype(int row, int col){
        return seats[row][col].getSeatt();
    }

    public String getseattypedesc(){
        String COUPLE_SEAT="Couple seat:", ELITE_SEAT="Elite seat:", ULTIMA_SEAT="Ultimate seat:";
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                if (seats[i][j].getSeatt() == Seattype.COUPLE_SEAT) {
                    char c = (char) (i+65);
                    COUPLE_SEAT = COUPLE_SEAT +" "+ String.valueOf(c)+ String.valueOf(j+1);
                }
                if (seats[i][j].getSeatt() == Seattype.ELITE_SEAT) {
                    char c = (char) (i+65);
                    ELITE_SEAT = ELITE_SEAT +" "+ String.valueOf(c)+ String.valueOf(j+1);
                }
                if (seats[i][j].getSeatt() == Seattype.ULTIMA_SEAT) {
                    char c = (char) (i+65);
                    ULTIMA_SEAT = ULTIMA_SEAT +" "+ String.valueOf(c)+ String.valueOf(j+1);
                }
            }
        }
        return COUPLE_SEAT+ELITE_SEAT+ULTIMA_SEAT;
    }
    
    public void setClass(ClassOfCinema c){
        this.cinemaClass = c;
    }

    public ClassOfCinema getCinemaClass(){
        return cinemaClass;
    }

    public void setID(String id){
        this.id = id;
    }

    public String getID(){
        return id;
    }


}
