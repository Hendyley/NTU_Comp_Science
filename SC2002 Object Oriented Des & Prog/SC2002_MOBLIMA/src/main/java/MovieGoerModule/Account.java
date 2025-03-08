package MovieGoerModule;

import java.io.Serializable;
import java.util.Set;

public abstract class Account implements Serializable{
    private String username;
    private String password;
    private Role role;

    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole(){
        return role;
    }

    public void setRole(Role role){
        this.role = role;
    }

    public abstract void printUserDetails();
}
