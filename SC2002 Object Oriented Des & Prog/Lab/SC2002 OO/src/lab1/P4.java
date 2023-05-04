package lab1;

import java.util.Scanner;

public class P4 {
    public static void main(String[] args){
        Scanner obj = new Scanner(System.in);

        int h = obj.nextShort();

        System.out.print("Height = "+h);

        if(h==0) {
            System.out.print("-Error");
        } else {
            System.out.println("");
            for(int x=1;x<=h;x++){

                for(int y=0; y<x ; y++){
                    if(y%2==(x%2)){
                        System.out.print("BB");
                    } else {
                        System.out.print("AA");
                    }
                }
                System.out.println("");
            }

        }
    }
}
