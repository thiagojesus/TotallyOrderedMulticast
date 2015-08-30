package principal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.PriorityQueue;

public class AddPacket implements Runnable {
	private Socket cs;
	private PriorityQueue<Packet> fila;
	public AddPacket(Socket cs, PriorityQueue<Packet> _fila) {
		super();
		this.cs = cs;
		this.fila = _fila;
	}
	@Override
	public void run() {
		try{
			ObjectInputStream inServer = new ObjectInputStream(cs.getInputStream());
			Packet p = (Packet) inServer.readObject();
			fila.add(p);
			//System.out.println("passei " + p.getSender() + " " + p.getTimestamp() + " " + cs.getPort());
		}catch(IOException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}

	}

}
