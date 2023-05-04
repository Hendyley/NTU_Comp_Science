package lab4;

import static java.lang.Math.PI;
import java.util.Scanner;

//abstract class Shape {
class Shape {

    //public abstract double area();
    public double area(){
        return 0;
    }

    public double volume(){
        return 0;
    }

    public double getSA(){
        return 0;
    }

}

class circle extends Shape {
    private double radius;

    public circle(double radius){
        this.radius = radius;
    }

    public double area(){
        return PI*radius*radius;
    }

    public double volume(){
        return 0;
    }
}

class triangle extends Shape {

    private double height,base;
    public triangle(double height, double base){
        this.height = height;
        this.base = base;
    }

    public double area(){
        return height*base/2;
    }

    public double volume(){
        return 0;
    }

}

class rectangle extends Shape {

    private double length,breath;

    public rectangle(double length,double breath){
        this.length = length;
        this.breath = breath;
    }

    public double area(){
        return length*breath;
    }

    public double volume(){
        return 0;
    }
}

class square extends Shape {

    private double length;

    public square(double length){
        this.length = length;
    }

    public double area(){
        return length*length;
    }

    public double volume(){
        return 0;
    }
}


public class Shape2DApp {

    public static void main(String[] args) {

        double radius, length, breath, height, base, total;
        int numofshape, choice;
        Shape[] shape;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter total number of shapes: ");
        numofshape = sc.nextInt();
        shape = new Shape[numofshape];
        for(int i=0;i<numofshape;i++){
            System.out.println("Select (0:circle) (1:rectangle) (2:square) (3:triangle): ");
            choice = sc.nextInt();
            switch (choice){
                case 0:
                    System.out.println("Enter radius: ");
                    radius = sc.nextDouble();
                    shape[i] = new circle(radius);
                    break;
                case 1:
                    System.out.println("Enter length: ");
                    length = sc.nextDouble();
                    System.out.println("Enter breath: ");
                    breath = sc.nextDouble();
                    shape[i] = new rectangle(length,breath);
                    break;
                case 2:
                    System.out.println("Enter length: ");
                    length = sc.nextDouble();
                    shape[i] = new square(length);
                    break;
                case 3:
                    System.out.println("Enter height: ");
                    height = sc.nextDouble();
                    System.out.println("Enter base: ");
                    base = sc.nextDouble();
                    shape[i] = new triangle(height,base);
                    break;
                default:
                    System.out.println("Invalid Shape");
                    shape[i] = new Shape();
                    break;
            }
        }
        System.out.println("Choose type of calculation");
        System.out.println("(0:Area) (1:Volume)");
        choice = sc.nextInt();
        switch (choice){
            case 0:
                total = 0;
                for(int i=0; i<numofshape; i++){
                    System.out.println("Area for shape "+i +" "+ shape[i].getClass().getName()+" is "+shape[i].area());
                    total += shape[i].area();
                }
                System.out.println("Total Area for "+numofshape+" shapes is " + total);
                break;
            case 1:
                total = 0;
                for(int i=0; i<numofshape; i++){
                    System.out.println("Volume for shape "+i +" "+ shape[i].getClass().getName()+" is "+shape[i].volume());
                    total += shape[i].volume();
                }
                System.out.println("Total Volume for "+numofshape+" shapes is " + total);
                break;
            default:
                System.out.println("Invalid selection");
                break;
        }


    }

}

