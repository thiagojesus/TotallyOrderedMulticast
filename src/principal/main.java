package principal;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Host h1 = new Host(0,3);
		Host h2 = new Host(1,3);
		Host h3 = new Host(2,3);
		Thread t1 = new Thread(h1);
		Thread t2 = new Thread(h2);
		Thread t3 = new Thread(h3);
		t1.start();
		t2.start();
		t3.start();
		t1.join();
		t2.join();
		t3.join();
		h1.print();
		h2.print();
		h3.print();

	}

}
