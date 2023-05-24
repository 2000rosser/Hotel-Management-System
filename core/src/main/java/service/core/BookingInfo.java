package service.core;

import java.time.LocalDate;

public class BookingInfo {
	
	public BookingInfo(int ID, int booking_ref, String type, int beds, double bedSize, boolean balcony, String view, boolean accessibility, LocalDate checkIn, LocalDate checkOut, int price) {
		this.ID = ID;
		this.booking_ref = booking_ref;
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
	
	
	public BookingInfo() {}
	
	public int ID;
	public int booking_ref;
	public String type;
	public int beds;
	public double bedSize;
	public boolean balcony;
	public String view;
	public boolean accessibility;
	public LocalDate checkIn;
	public LocalDate checkOut;
	public int price;
}
