package service.core;


public class Admin {
	
	public Admin(String username, String password, String hotel) {
        this.username = username;
        this.password = password;
        this.hotel = hotel;
	}

	
	
	public String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }



    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }


    public Admin() {}
	
	public String username;
	public String password;
    public String hotel;
}