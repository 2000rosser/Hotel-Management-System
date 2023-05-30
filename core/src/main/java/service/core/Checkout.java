package service.core;


public class Checkout {
	
	public Checkout(int booking_ref, String name, String email) {
		this.booking_ref = booking_ref;
		this.name = name;
		this.email = email;
	}

	
	
	public Checkout() {}
	
	public int booking_ref;
	public String name;
	public String email;
}
