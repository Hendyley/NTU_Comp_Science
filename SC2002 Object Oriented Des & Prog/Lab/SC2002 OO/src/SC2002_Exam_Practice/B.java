package SC2002_Exam_Practice;

public class B {

    public String b1 = "Bitch ";
    private String b2 = "Private Bitch ";
    protected String b3 = "Protected Bitch ";

    double bn1,bn2;

    public B(){

    }
    public B(double bn1, double bn2){
        this.bn1=bn1;
        this.bn2=bn2;
    }

    public double getBn1(){
        return bn1;
    }

    public double getBn2(){
        return bn2;
    }
    public String getB1(){
        return b1;
    }

    public String getB2() {
        return b2;
    }

    public String getB3() {
        return b3;
    }
    public void call(){
        System.out.println("call B");
    }
}
