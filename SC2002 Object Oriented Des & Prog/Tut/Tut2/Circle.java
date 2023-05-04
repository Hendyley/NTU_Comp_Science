public class Circle
{
	private double radius; // radius of circle
	private static final double PI = 3.14159;// Math.PI
	// constructor
	public Circle(double radius) {
		setRadius(radius) ;
	}
	// mutator method – set radius
	public void setRadius(double rad){
		this.radius = rad ;
	}
	// accessor method – get radius
	public double getRadius(){ return radius;}
	// calculate area
	public double area(){
		return Math.PI*Math.pow(radius,2);
	}
	// calculate circumference
	public double circumference() {
		return 2*PI * radius;
	}
	// print area
	public void printArea(){
		System.out.println("Area of circle");
		System.out.println("Radius: " + radius);
		System.out.println("Area: " + area());
	}
	// print circumference
	public void printCircumference(){
		System.out.println("Circumference of circle");
		System.out.println("Radius: " + radius);
		System.out.println("Circumference: " + circumference());
	}
}