package principal;

import java.util.PriorityQueue;
import java.util.Random;

public class Host implements Runnable {

	private PriorityQueue<Packet> fila = new PriorityQueue<Packet>(3,OrderPacketByTimestamp.INSTANCE);
	private int id;
	private int size;
	
	public Host(int _id, int groupSize){
		this.id = _id;
		this.size = groupSize;
	}
	
	public void print(){
		System.out.println("HOST "+id);
		for(int i = 0; i < size; i++){
			//System.out.println(fila.size());
			Packet pct = fila.poll();
			System.out.println("Remetente: "+pct.getSender()+" Relógio: "+pct.getTimestamp());
		}
	}
	
	@Override
	public void run() {
		Thread server = new Thread(new Server(id, fila));
		server.start();
		Random c = new Random();
		Packet p = new Packet(c.nextLong(), id);
		for(int i=0;i<size;i++){
			new Thread(new Client(i, p)).start();
		}
		try {
			server.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
