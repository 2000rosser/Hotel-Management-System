package service.core;
import java.time.LocalDate;

/**
 * Class to store the quotations returned by the quotation services
 * 
 * @author Rem
 *
 */
public class Quotation{
	public Quotation() {
        // Default constructor
    }
	
	public Quotation(String company, String reference, double totalPrice, RoomInfo roomInfo) {
		this.company = company;
		this.reference = reference;
		this.totalPrice = totalPrice;
		this.roomInfo = roomInfo;
	}
	
	public String company;
	public String reference;
	public double totalPrice;
	public RoomInfo roomInfo;
}

