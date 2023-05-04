package lab1;

import java.util.Scanner;

public class P3 {

    static void Head() {
        System.out.println("US$\t\t\tS$\n");
        System.out.println("----------------\n");
    }

    public static void main(String[] args){

        Scanner obj = new Scanner(System.in);

        int s,e,i,a = 0;


        System.out.print("starting : ");
        s = obj.nextShort();
        System.out.print("ending : ");
        e = obj.nextShort();
        System.out.print("increment : ");
        i = obj.nextShort();

        if(s>e){
            System.out.println("-Error\n");
        }
        else{
            a=0;
            Head();
            for (a = s; a <= e; a+=i){
                System.out.println(a+"\t\t\t"+a*1.82);
            }
        }


        System.out.print("starting : ");
        s = obj.nextShort();
        System.out.print("ending : ");
        e = obj.nextShort();
        System.out.print("increment : ");
        i = obj.nextShort();

        if(s>e){
            System.out.println("-Error\n");
        }
        else{
            Head();
            a = 0;
            while (a <= e) {
                System.out.println(a + "\t\t\t" + a * 1.82);
                a += i;
            }
        }


        System.out.print("starting : ");
        s = obj.nextShort();
        System.out.print("ending : ");
        e = obj.nextShort();
        System.out.print("increment : ");
        i = obj.nextShort();

        if(s>e){
            System.out.println("-Error\n");
        }
        else{
            Head();
            a=s;
            do{
                System.out.println(a+"\t\t\t"+a*1.82);
                a+=i;
            }while(a<=e);
        }


    }
}
