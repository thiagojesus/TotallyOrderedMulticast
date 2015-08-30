package principal;

import java.io.Serializable;

public class Packet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long timestamp;
	private int sender; 
	
	public int getSender() {
		return sender;
	}

	public Packet(long _timestamp, int _sender){
		this.sender = _sender;
		this.timestamp = _timestamp;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
}
