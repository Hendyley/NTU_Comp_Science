package AdminModule;
import MovieGoerModule.Account;
import MovieGoerModule.Role;

public class Staff extends Account {

    public Staff(String username, String password) {
        super(username,password);
        super.setRole(Role.ADMIN);
    }

    public void printUserDetails(){
        System.out.printf("Username: %s\n",super.getUsername());
        System.out.printf("Role: %s\n",super.getRole());
    }
}
