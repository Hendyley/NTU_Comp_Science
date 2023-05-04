package lab4;

import java.util.Scanner;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;


class sphere extends circle{

    public sphere(double radius) {
        super(radius);
    }

    public double getSA(){
        return 4*this.area();
    }
}

class pyramid extends triangle{

    private double height,base;

    public pyramid(double height, double base) {
        super(height, base);
        this.height = height;
        this.base = base;
    }

    public double getSA(){
        return base*base+2*base*sqrt(base*base/4+height*height);
    }

}

class cubinoid extends rectangle{

    private double height,length,breath;

    public cubinoid(double length, double breath, double height) {
        super(length, breath);
        this.height = height;
        this.length = length;
        this.breath = breath;
    }

    public double getSA(){
        return 2*(height*length+ this.area() +height*breath);
    }

}

// Part 3
class cone extends pyramid{

    private double height,base,radius;

    public cone(double height, double radius) {
        super(height, 0);
        this.radius = radius;
        this.height = height;

    }

    public double getSA(){
        return PI*radius*(radius+sqrt(height*height+radius*radius));
    }

}

class cylinder extends cubinoid{

    double length,breath,height,radius;

    public cylinder(double height, double radius) {
        super(0,0,height);
        this.height=height;
        this.radius=radius;
    }
    public double getSA(){
        return 2*PI*radius*(radius+height);
    }
}

public class Shape3DApp{
    public static void main(String[] args) {

        double radius, length, breath, height, base, total;
        int numofshape, choice;
        Shape[] shape;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter total number of shapes: ");
        numofshape = sc.nextInt();
        shape = new Shape[numofshape];
        for(int i=0;i<numofshape;i++){
            System.out.println("Select (0:sphere) (1:cubinoid) (2:pyramid) (3:cylinder) (4:cone) ");
            choice = sc.nextInt();
            switch (choice){
                case 0:
                    System.out.println("Enter radius: ");
                    radius = sc.nextDouble();
                    shape[i] = new sphere(radius);
                    break;
                case 1:
                    System.out.println("Enter length: ");
                    length = sc.nextDouble();
                    System.out.println("Enter breath: ");
                    breath = sc.nextDouble();
                    System.out.println("Enter height: ");
                    height = sc.nextDouble();
                    shape[i] = new cubinoid(length,breath,height);
                    break;
                case 2:
                    System.out.println("Enter height: ");
                    height = sc.nextDouble();
                    System.out.println("Enter base: ");
                    base = sc.nextDouble();
                    shape[i] = new pyramid(height,base);
                    break;
                case 3:
                    System.out.println("Enter height: ");
                    height = sc.nextDouble();
                    System.out.println("Enter radius: ");
                    radius = sc.nextDouble();
                    shape[i] = new cylinder(height,radius);
                    break;
                case 4:
                    System.out.println("Enter height: ");
                    height = sc.nextDouble();
                    System.out.println("Enter radius: ");
                    radius = sc.nextDouble();
                    shape[i] = new cone(height,radius);
                    break;
                default:
                    System.out.println("Invalid Shape");
                    shape[i] = new Shape();
                    break;
            }
        }
        System.out.println("Choose type of calculation");
        System.out.println("(0:Total Surface Area)");
        choice = sc.nextInt();
        switch (choice){
            case 0:
                total = 0;
                for(int i=0; i<numofshape; i++){
                    System.out.println("Surface area for shape "+i +" "+ shape[i].getClass().getName()+" is "+shape[i].getSA());
                    total += shape[i].getSA();
                }
                System.out.println("Total Surface Area for "+numofshape+" shapes is " + total);
                break;
            default:
                System.out.println("Invalid selection");
                break;
        }

    }
}
