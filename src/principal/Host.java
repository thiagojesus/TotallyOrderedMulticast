package principal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Host implements Runnable {

	private PriorityQueue<Packet> fila = new PriorityQueue<Packet>(3,OrderPacketByTimestamp.INSTANCE);
	private int id;
	private ServerSocket incoming;
	private ArrayList<Socket> outSockets;
	private ArrayList<ObjectOutputStream> outSocketsOS;
	private ObjectInputStream inServer;
	private int counter;
	
	public Host(int _id, int groupSize){
		this.id = _id;
		this.counter = 0;
		
		Thread server = new Thread(new createSocket());
		server.start();
		
		outSockets = new ArrayList<Socket>();
		outSocketsOS = new ArrayList<ObjectOutputStream>();
		for(int i = 0; i<groupSize; i++){
			try {
				outSockets.add(new Socket("localhost",1234+i));
				outSocketsOS.add(new ObjectOutputStream(outSockets.get(i).getOutputStream()));
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void send(){
		try {
			for(ObjectOutputStream ob: outSocketsOS){
				Packet pct = new Packet(counter, id);
				ob.writeObject(pct);
				System.out.println("escrevi" + pct.getTimestamp());
				counter++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public class createSocket implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				incoming = new ServerSocket(1234+id);
				Socket incomingSocket = incoming.accept();
				inServer = new ObjectInputStream(incomingSocket.getInputStream());
				
			} catch (IOException e) {
				// TODO: handle exception
			}
		}
		
	}
	
	@Override
	public void run() {
		int i = 0;
		
		if(i==0){
			send();
			i++;
		}
		while(true){
			try {
				Packet p = (Packet)inServer.readObject();
				fila.add(p);
				counter++;
				System.out.println("passei "+ p.getSender() + " " + p.getTimestamp());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
