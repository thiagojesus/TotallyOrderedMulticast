package principal;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements Runnable {
	Socket cs;
	int id;
	Packet p;
	Client(int _id, Packet _p){
		this.id = _id;
		this.p = _p;
	}
	@Override
	public void run() {
		try {
			cs = new Socket("localhost",1234+id);
			ObjectOutputStream os = new ObjectOutputStream(cs.getOutputStream());
			os.writeObject(p);
			cs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
