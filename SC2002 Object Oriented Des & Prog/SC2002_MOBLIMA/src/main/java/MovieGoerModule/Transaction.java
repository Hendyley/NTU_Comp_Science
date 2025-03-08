package MovieGoerModule;

import java.io.Serializable;

public class Transaction implements Serializable{
    private String id;
    private String mgName;
    private int mgMobile;
    private String mgEmail;
    private double totalPrice;
    private Ticket[] tickets;

    public Transaction(String id, Ticket[] tickets) {
        this.id = id;
        this.tickets = tickets;
    }

    public Transaction(String id, String mgName, int mgMobile, String mgEmail, Ticket[] tickets) {
        this.id = id;
        this.mgName = mgName;
        this.mgMobile = mgMobile;
        this.mgEmail = mgEmail;
        this.tickets = tickets;
        this.totalPrice = calcTotalPrice(tickets);
    }

    public String getId() {
        return this.id;
    }

    public String getMgName(){
        return this.mgName;
    }

    public int getMgMobile(){
        return this.mgMobile;
    }

    public String getMgEmail(){
        return this.mgEmail;
    }

    public Ticket[] getTickets() {
        return tickets;
    }

    public double getTotalPrice(){
        return totalPrice;
    }


    public void printTickets() {
        for (int i = 0; i < tickets.length; i++) {
            System.out.println(" Ticket "+id.substring(0,id.indexOf("Cinema"))+"age category: "+tickets[i].getAgetype()+" seat:"+tickets[i].getSeattype()+" price:"+tickets[i].getPrice()+" purchased:"+tickets[i].getDay()+" qty:"+tickets[i].getQuantity());
        }
    }

    public double calcTotalPrice(Ticket[] ticketList){
        double totalPrice = 0;
        for(Ticket t : ticketList){
            totalPrice += t.getPrice();
        }
        return totalPrice;
    }
}
