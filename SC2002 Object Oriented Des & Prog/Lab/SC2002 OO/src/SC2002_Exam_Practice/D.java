package SC2002_Exam_Practice;

import java.util.ArrayList;
import java.util.List;

public class D implements I{

    public List<A> la = new ArrayList<A>();

    public D(List<A> la){
        this.la = la;
    }

    @Override
    public double iint() {
        return 0;
    }

    @Override
    public void print() {
        System.out.println("Print D ");
    }

    @Override
    public void call() {
        System.out.println("Call D ");
    }

}
