package MovieGoerModule;

import java.util.ArrayList;

public class MovieGoer extends Account{
    private String name;

    private int age; //see how
    private AgeOfMovieGoer agetype;

    private int mobile;
    private String email;
    private ArrayList<Transaction> history;


    public MovieGoer(String username, String password) {
        super(username,password);
        super.setRole(Role.MOVIEGOER);
        this.name = "";
        this.email = "";
        this.mobile = -1;
        this.history = new ArrayList<>();
    }

    public MovieGoer(String username, String password,String name, int mobile, String email) {
        super(username,password);
        super.setRole(Role.MOVIEGOER);
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.history = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public int getMobile() {
        return this.mobile;
    }

    public String getEmail() {
        return email;
    }

    public int getAge(){return age;}


    public AgeOfMovieGoer getAgetype() {
        return agetype;
    }

    public ArrayList<Transaction> getTransactionHistory(){
        return this.history;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setAge(int age){
        //CHILD, ADULT, SENIOR, STUDENT
        this.age = age;
        if(age>54){
            agetype = AgeOfMovieGoer.SENIOR;
        } else if (age>20) {
            agetype = AgeOfMovieGoer.ADULT;
        } else if (age>10) {
            agetype = AgeOfMovieGoer.STUDENT;
        } else {
            agetype = AgeOfMovieGoer.CHILD;
        }
    }
    
    public void addTransaction(Transaction trans){
        this.history.add(trans);
    }

    public void printUserDetails(){
        System.out.printf("Username: %s\n",super.getUsername());
        System.out.printf("Role: %s\n",super.getRole());
        System.out.printf("Name: %s\n",this.name);
        System.out.printf("Mobile: %d\n",this.mobile);
        System.out.printf("Email: %s\n",this.email);
    }
}
