package AdminModule;

public class dateChecker {
    
    public static boolean check(String date){
        return date.matches("^(0[1-9]|[1-2][0-9]||3[0-1])/(0[1-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$");
    }

}
