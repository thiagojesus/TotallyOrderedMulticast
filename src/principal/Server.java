package principal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.PriorityQueue;

public class Server implements Runnable {
	ServerSocket incoming;
	int id;
	boolean isUp;
	private PriorityQueue<Packet> fila;
	Server(int _id, PriorityQueue<Packet> _fila){
		this.id = _id;
		this.fila = _fila;
		isUp = true;
	}
	
	@Override
	public void run(){
		try {
			incoming = new ServerSocket(1234+id);
			incoming.setSoTimeout(3000);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			try {
				incoming.close();
				isUp = false;
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		while (isUp) {
			try {
				Socket acc = incoming.accept();
				new Thread(new AddPacket(acc, fila)).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				isUp = false;
			}
		}

	}
}
