import java.util.Scanner;

public class DiceApp
{
	public static void main(String[] args)
	{
		int diceValue ;
		Scanner sc = new Scanner(System.in);
		System.out.println("Press <key> to roll the first dice");

		sc.nextLine();

		Dice d = new Dice() ;
		diceValue = d.getDiceValue() ;
		d.printDiceValue() ;

		System.out.println("Press <key> to roll the second dice");
		sc.nextLine();
		d.setDiceValue();
		diceValue += d.getDiceValue() ;
		d.printDiceValue() ;

		System.out.println("Your total number is: " +  diceValue);
	}
}