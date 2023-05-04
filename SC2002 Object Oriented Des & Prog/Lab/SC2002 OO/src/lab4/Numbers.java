package lab4;

/*
	Error message: Incompatible types: int[] cannot be converted to Comparable<Object>[]
	https://stackoverflow.com/questions/26517632/incompatible-types-int-cannot-be-converted-to-comparableobject

	You have two issues here:

	int is a POD ("plain old data type") , not an object.
	Boxing and unboxing from int[] to corresponding Integer[] is not automatically performed.
	You need to declare intArray as:

	Integer[] <name>;

	Integer implements Comparable<Integer>, not Comparable<Object>.
	The two specializations are different and incompatible types.
	The right way to declare selectionSort is by using generics, as you suggest in the title:

	public static <T extends Comparable<T>> void selectionSort(T[] input)


	!!!!!!
	Autoboxing and Unboxing
	Autoboxing is the automatic conversion that the Java compiler makes between the primitive types and
	their corresponding object wrapper classes.

	For example, converting an int to an Integer, a double to a Double, and so on.
	If the conversion goes the other way, this is called unboxing.


*/

import java.util.Scanner;
public class Numbers
{
	// --------------------------------------------
	// Reads in an array of integers, sorts them,
	// then prints them in sorted order.
	// --------------------------------------------

	public static void main(String[] args) {

		//int[] intList;
		Integer[] intList;    ////!!!!!!!!!!!!!!!!!!
		int size;
		Scanner scan = new Scanner(System.in);

		System.out.print ("\nHow many integers do you want to sort? ");
		size = scan.nextInt();
		//intList = new int[size];   ////!!!!!!!!!!!!!!!!

		intList = new Integer[size];

		System.out.println ("\nEnter the numbers...");
		for (int i = 0; i < size; i++)
			intList[i] = scan.nextInt();
			Sorting.selectionSort(intList);
			Sorting.insertionSort(intList); //////
			System.out.println ("\nYour numbers in sorted order...");
		for (int i = 0; i < size; i++)
			System.out.print(intList[i] + " ");
		System.out.println ();
		}
}