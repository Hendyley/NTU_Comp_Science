package lab4;
/*
https://stackoverflow.com/questions/25556961/negative-and-positive-return-values-of-compare-and-compareto
 */

import java.util.Comparator;

public class SalePerson implements Comparable {

    private String firstName;
    private String lastName;
    private int totalSales;

    public SalePerson(String firstName, java.lang.String lastName, int totalSales) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalSales = totalSales;
    }

    public String toString(){
        return lastName+" "+firstName+"\t\t\t:"+totalSales;
    }
/*
    public boolean equals(SalePerson o){
        return o.firstName==o.lastName;
    }

    public int compareTo(SalePerson o){
        return Integer.compare(this.totalSales, o.totalSales);
        //return totalSales;
    }

*/

    @Override
    public boolean equals(Object o){
        SalePerson s = (SalePerson)o;

        if ( (this.firstName==s.firstName) && (this.lastName==s.lastName) ){
            return true;
       }
        return false;
        //return (this.firstName+this.lastName == s.firstName+s.lastName);
    }

    public int compareTo(Object o) {
        SalePerson s = (SalePerson)o;

        if(this.totalSales > s.totalSales){
            return 1;
        }
        else if (this.totalSales < s.totalSales){
            return -1;
        }
        else{
            if(this.lastName.compareTo(s.lastName) < 0){
                return 1;
            }
            else {
                return -1;
            }
        }

    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public int getTotalSales(){
        return totalSales;
    }


}
