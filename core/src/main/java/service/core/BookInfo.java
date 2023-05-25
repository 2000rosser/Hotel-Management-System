package service.core;
import service.core.RoomInfo;

public class BookInfo {

    public BookInfo(Quotation quote, String name, String email, String phone) {
        this.quote = quote;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public BookInfo() {}

    public Quotation quote;
    public String name;
    public String email;
    public String phone;

    public void setQuotation(Quotation quote) {
        this.quote = quote;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
    	this.phone = phone;
    }

    public void setEmail(String email) {
    	this.email = email;
    }


    
}
