package lab1;

import java.util.Scanner;



/*
if  ((choice == 'A')  || (choice == 'a'))
    printf("Action movie fan\n");
else if  ((choice == 'C')  || (choice == 'c'))
    printf("Comedy movie fan\n");
else if  ((choice == 'D')  || (choice == 'd'))
    printf("Drama movie fan\n");
else
    printf("Invalid choice\n");
*/

public class P1 {
    public static void main(String[] args) {

        Scanner userinput = new Scanner(System.in);

        //char choice = userinput.next().charAt(0);

        String word = userinput.nextLine();

        for (char choice: word.toCharArray()) {
            switch(choice){
                case 'A':
                case 'a':
                    System.out.println("Action movie fan\n");
                    break;
                case 'C':
                case 'c':
                    System.out.println("Comedy movie fan\n");
                    break;
                case 'D':
                case 'd':
                    System.out.println("Drama movie fan\n");
                    break;
                default:
                    System.out.println("Invalid choice\n");
            }
        }

        //System.out.println("Hello! This is my first program.");
        //System.out.println("Bye Bye!s");
    }
}

