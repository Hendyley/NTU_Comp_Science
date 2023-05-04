package lab3;

import java.util.*;

public class Plane {

    private PlaneSeat[] seat = new PlaneSeat[12];
    //PlaneSeat seat = new PlaneSeat(12);
    private int numEmptySeat = 12;

    public Plane() {

    }

    private PlaneSeat[] sortSeats() {
        PlaneSeat[] bycust = new PlaneSeat[12 - numEmptySeat];
        //bycust = seat.clone();

        int n = 0;
        for (int i = 0; i < seat.length; i++) {
            if (seat[i] != null) {
                bycust[n] = new PlaneSeat(seat[i].getSeatID(), seat[i].isOccupied(), seat[i].getCustomerID());
                //System.out.println(bycust[n].getCustomerID());
                n++;
            }
        }

        //System.out.println(bycust[1].getCustomerID());

        for (int i = 1; i < bycust.length; i++) {

            if (bycust[i].getCustomerID() < bycust[i - 1].getCustomerID()) {
                PlaneSeat x = bycust[i];
                bycust[i] = bycust[i - 1];
                bycust[i - 1] = x;
                i--;
            }

        }

        return bycust;
    }

    public void showNumEmptySeats() {
        System.out.println("There are " + numEmptySeat + " empty seats");

    }

    public void showEmptySeats() {
        System.out.println("The following seats are empty:");
        for (int i = 0; i < 12; i++) {
            if (seat[i] == null) {
                System.out.println("SeatID " + (i + 1));
            }
        }

    }

    public void showAssignedSeats(boolean bySeatId) {
        if (bySeatId) {
            for (int i = 0; i < 12; i++) {
                if (seat[i] != null) {
                    System.out.println("SeatID " + seat[i].getSeatID() + " assigned to CustomerID " + seat[i].getCustomerID());
                }
            }
        } else {
            PlaneSeat[] bycust = sortSeats();
            for (int i = 0; i < bycust.length; i++) {
                if (bycust[i] != null) {
                    System.out.println("SeatID " + (bycust[i].getSeatID()) + " assigned to CustomerID " + bycust[i].getCustomerID());
                }
            }

        }

    }

    public void assignSeat(int seatId, int cust_id) {

        if (seat[seatId - 1] == null) {
            seat[seatId - 1] = new PlaneSeat(seatId, true, cust_id);
            System.out.println("Seat Assigned!");

            numEmptySeat--;
        } else {
            System.out.println("Seat already assigned to a customer.");
        }


    }

    public void unAssignSeat(int seatId) {
        if (seat[seatId - 1] == null) {
            System.out.println("It has not been assigned!!");
        } else {
            //seat[seatId-1].unAssign();
            seat[seatId - 1] = null;
            System.out.println("Seat Unassigned!");

            numEmptySeat++;
        }

    }


}
