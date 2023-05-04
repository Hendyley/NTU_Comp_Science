package tutorial;

public class Cylinder extends Circle{
	private double height;
	
	public Cylinder(double height, double radius, Point center) {
		super(radius, center);
		this.height = height;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setHeight(double h) {
		height = h;
	}
	
	public double area() {
		return 2*super.area()+super.circumference()*height;
	}
	
	public double volume() {
		return super.area()*height;
	}

}
