package service.core;

import java.time.LocalDate;

public class BookingInfo {
	
	public BookingInfo(int ID, Quotation quote, String name, String email, String phone, int booking_ref, String type, int beds, double bedSize, boolean balcony, String view, boolean accessibility, LocalDate checkIn, LocalDate checkOut, int price) {
		this.ID = ID;
		this.quote = quote;
		this.name = name;
		this.email = email;
		this.phone = phone;
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
	public Quotation quote;
	public String name;
	public String email;
	public String phone;
	public int booking_ref;
	public String type;
	public int beds;
	public double bedSize;
	public boolean balcony;
	public String view;
	public boolean accessibility;
	public LocalDate checkIn;
	public LocalDate checkOut;
	public double price;
}
