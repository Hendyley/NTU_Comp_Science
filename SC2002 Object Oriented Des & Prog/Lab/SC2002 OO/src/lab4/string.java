package lab4;

import java.util.Scanner;

public class string {

    public static void main(String[] args) {

        //int[] intList;
        java.lang.String[] strList;    ////!!!!!!!!!!!!!!!!!!
        int size;
        Scanner scan = new Scanner(System.in);

        System.out.print ("\nHow many word do you want to sort? ");
        size = scan.nextInt();
        //intList = new int[size];   ////!!!!!!!!!!!!!!!!

        strList = new java.lang.String[size];

        System.out.println ("\nEnter the String...");
        for (int i = 0; i < size; i++)
            strList[i] = scan.next();
        Sorting.selectionSort(strList);
        Sorting.insertionSort(strList);
        System.out.println ("\nYour numbers in sorted order...");
        for (int i = 0; i < size; i++)
            System.out.print(strList[i] + " ");
        System.out.println ();
    }
}
