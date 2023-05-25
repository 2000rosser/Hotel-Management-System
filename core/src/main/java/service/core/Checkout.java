package service.core;


public class Checkout {
	
	public Checkout(int id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	
	
	public Checkout() {}
	
	public int id;
	public String name;
	public String email;
}
