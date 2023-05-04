package lab2;

import java.util.Scanner;

public class Lab2p1 {

    public static void mulTest() {

        int count=0;

        for(int i=0;i<5;i++){
            // define the range
            int max = 10;
            int min = 1;
            int range = max - min + 1;

            int a = (int)(Math.random() * range) + min;
            int b = (int)(Math.random() * range) + min;
            Scanner sc = new Scanner(System.in);
            System.out.println("How much is "+a+" times "+b+"?");
            int ans = sc.nextInt();

            if(ans == a*b){
                count++;
            }
        }

        System.out.println(count+" answer out of 5 are correct.");

    }

    public static int divide(int m,int n) {
        int count = 0;
        for(int i=m;(i-n)>=0;i-=n){
            count++;
        }
        return count;

    }

    public static int modulus(int m, int n){
        int c = m;
        for(int i=m;(i-n)>=0;i-=n){
            c = i-n;
        }
        return c;

    }

    public static int countDigits(int n){
        int c=0;
        if(n<0){
            return -1;
        }

        do{
            n/= 10;
            ++c;
        } while (n != 0);

        return c;
    }

    public static int position(int n,int digit){
        int c=1, rem=0;

        do{

            if(n % 10 == digit){
                return c;
            }
            n /= 10;
            ++c;

        } while (n != 0);

        return -1;
    }

    public static long extractOddDigits(long n){
        long x=0,m=1;

        if(n<0){
            return -2;
        }

        do{
            if( (n%10) %2 == 1){
                x += (n%10) * m;
                m *= 10;
            }
            n /= 10;

        } while(n != 0);

        if(x == 0){
            return -1;
        }

        return x;
    }

    public static void main(String[] args) {
        int choice;
        int m,n,digit =0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Perform the following methods:");
            System.out.println("1:  multiplication test");
            System.out.println("2:  quotient using division by subtraction");
            System.out.println("3:  remainder using division by subtraction");
            System.out.println("4:  count  the  number of digits");
            System.out.println("5:  position of a digit");
            System.out.println("6:  extract all odd digits");
            System.out.println("7:  quit");
            choice = sc.nextInt();

            switch (choice) {
                case 1: /* add mulTest() call */
                    mulTest();
                    break;
                case 2: /* add divide() call */
                    System.out.println("m,n: ");
                    m = sc.nextInt();
                    n = sc.nextInt();
                    System.out.println(m+"/"+n+"="+ divide(m,n));
                    break;
                case 3: /* add modulus() call */
                    System.out.println("m,n: ");
                    m = sc.nextInt();
                    n = sc.nextInt();
                    System.out.println(m+"%"+n+"="+ modulus(m,n));
                    break;
                case 4: /* add countDigits() call */
                    System.out.println("n: ");
                    n = sc.nextInt();
                    int x = countDigits(n);
                    if (x<0){
                        System.out.println("n: "+n+"- Error Input");
                    } else{
                        System.out.println("n: "+n+"- count ="+ x);
                    }
                    break;
                case 5: /* add position() call */
                    System.out.println("n,digit: ");
                    n = sc.nextInt();
                    digit = sc.nextInt();
                    System.out.println("Position: "+ position(n,digit) );
                    break;
                case 6: /* add extractOddDigits() call */
                    System.out.println("n: ");
                    long l = sc.nextLong();
                    long l2 = extractOddDigits(l);
                    if(l2==-2){
                        System.out.println("Error input!!");
                    } else {
                        System.out.println("oddDigits = "+ l2);
                    }
                    break;
                case 7:
                    System.out.println("Program terminating ….");
            }
        } while (choice < 7);
    }

    /* add method code here */

}
