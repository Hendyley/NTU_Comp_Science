package SC2002_Exam_Practice;

public class A extends B {

    double a;

    public A(double a,double a2, double a3){
        super(a,a2);
        this.a = a3;
    }

    public void call(){
        System.out.println("call A");
    }

}
