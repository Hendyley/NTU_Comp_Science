package MovieGoerModule;

import java.io.Serializable;

public class Seat implements Serializable{
    private boolean seat;

    private Seattype seattype;

    public Seat() {
        this.seat = false;
        this.seattype = Seattype.SEAT;
    }

    public boolean isTaken() {
        return seat;
    }

    public void bookSeat() {
        this.seat = true;
    }

    public void setSeatt(Seattype seattype) {
        this.seattype = seattype;
    }
    public Seattype getSeatt(){
        return seattype;
    }


}
