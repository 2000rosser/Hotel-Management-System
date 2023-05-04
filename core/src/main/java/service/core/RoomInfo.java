package service.core;

public class RoomInfo {
	
	public RoomInfo(String type, int beds, double bedSize, boolean balcony, String view, boolean accessibility, String checkIn, String checkOut, int price) {
		this.type = type;
		this.beds = beds;
		this.bedSize = bedSize;
		this.balcony = balcony;
		this.view = view;
		this.accessibility = accessibility;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.price = price;
	}

	//guest names array
	//reservation ID
	
	
	public RoomInfo() {}
	
	public String type;
	public int beds;
	public double bedSize;
	public boolean balcony;
	public String view;
	public boolean accessibility;
	public String checkIn;
	public String checkOut;
	public int price;
}
