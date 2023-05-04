public class Dice
{
	private int valueOfDice=1;
	// constructor
	public Dice() {
		setDiceValue();
	}
	// mutator method - set the dice roll
	public void setDiceValue() {
		valueOfDice = (int)(Math.random()*6) + 1;
		//Random r = new Random();
		//valueOfDice = r.nextInt(6) + 1 ;
	}
	// accessor method - get the dice value
	public int getDiceValue() { return valueOfDice ;}
	// print the dice value
	public void printDiceValue() {
		System.out.println("Current Value is " + valueOfDice);
	}
	protected void finalize()  {
		System.out.println("cleaning up...");
	}
}