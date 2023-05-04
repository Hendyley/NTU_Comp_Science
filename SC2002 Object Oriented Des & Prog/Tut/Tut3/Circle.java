package tutorial;

public class Circle{
	
	private double radius;
	private Point center;
	
	public Circle(double radius, Point center) {
		this.center = center;
		this.radius = radius;
		
	}
	
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public double area() {
		return Math.PI*radius*radius;
	}
	
	public double circumference() {
		return 2*Math.PI*radius;
	}
	
	public String toString() {
		return "the circle has the radius of " + radius + "; its center is located at " 
				+ center.toString() + "; and its area is " + area();
	}

}
