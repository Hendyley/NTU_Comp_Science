package tutorial;

public class Test {

	public static void main(String[] args) {
		
		Point point = new Point(4, 5);
		System.out.println(point.toString());
		
		Circle circle = new Circle(5.0, point);
		System.out.println(circle.toString());
		
		Cylinder cylinder = new Cylinder(7.0, 5.0, point);
		System.out.println("the area of the cylinder is " + cylinder.area());
		
	}
	
}
