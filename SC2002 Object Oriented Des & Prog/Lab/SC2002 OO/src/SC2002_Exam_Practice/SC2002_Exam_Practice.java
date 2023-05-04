package SC2002_Exam_Practice;//Exam


import java.util.ArrayList;
import java.util.List;

public class SC2002_Exam_Practice{




	public static void main(String args[]){
		A a = new A(3,3,3);
		B b = new B(1,1);

		B b1 = new A(2,2,2);
		ab c = new C();

		List<A> la = new ArrayList<A>();
		la.add(a);
		D d = new D(la);
		E e = new E(1,1,1);


		String allstring = b.getB1() + b.getB2() + b.getB3();
		//getpublicz();
		System.out.println(allstring + b.b3 + a.b3 + a.bn1 +" "+ b.bn1+" ");
		a.call();
		b.call();
		c.call();
		d.la.get(0).call();

		b1.call();
	}

}
