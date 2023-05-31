package service.core;

public class RoomInfo {
	
	public RoomInfo(int id, String type, int beds, double bedSize, boolean balcony, String view, boolean accessibility, String checkIn, String checkOut, int price, String hotel) {
		this.id = id;
		this.type = type;
		this.beds = beds;
		this.bedSize = bedSize;
		this.balcony = balcony;
		this.view = view;
		this.accessibility = accessibility;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.price = price;
		this.hotel = hotel;
	}	

	
	
	public RoomInfo() {}
	
	public int id;
	public String type;
	public int beds;
	public double bedSize;
	public boolean balcony;
	public String view;
	public boolean accessibility;
	public String checkIn;
	public String checkOut;
	public int price;
	public String hotel;
}
