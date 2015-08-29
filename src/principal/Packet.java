package principal;

import java.io.Serializable;

public class Packet implements Serializable {
	private int timestamp;
	private int sender; 
	
	public int getSender() {
		return sender;
	}

	public Packet(int _timestamp, int _sender){
		this.sender = _sender;
		this.timestamp = _timestamp;
	}
	
	public int getTimestamp() {
		return timestamp;
	}
}
