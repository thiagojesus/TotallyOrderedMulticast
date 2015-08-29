package principal;

import java.util.Comparator;

public class OrderPacketByTimestamp implements Comparator<Packet> {
	public static final OrderPacketByTimestamp INSTANCE = new OrderPacketByTimestamp();
	
	private OrderPacketByTimestamp(){}
	
	@Override
	public int compare(Packet p1, Packet p2){
		return Integer.valueOf(p1.getTimestamp()).compareTo(p2.getTimestamp());
	}
	
	@Override
	public boolean equals(Object other){
		return other == OrderPacketByTimestamp.INSTANCE;
	}
	
	private Object readResolve(){
		return INSTANCE;
	}
}
