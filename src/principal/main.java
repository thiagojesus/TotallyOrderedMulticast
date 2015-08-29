package principal;


public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 = new Thread(new Host(0,3));
		Thread t2 = new Thread(new Host(1, 3));
		Thread t3 = new Thread(new Host(2,3));
		t1.start();
		t2.start();
		t3.start();
	}

}
