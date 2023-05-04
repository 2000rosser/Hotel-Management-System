package service.hotel1;

import service.core.AbstractQuotationService;
import service.core.RoomInfo;
import service.core.Quotation;


public class Hotel1Service extends AbstractQuotationService {
	// All references are to be prefixed with an AF (e.g. AF001000)
	public static final String PREFIX = "AF";
	public static final String COMPANY = "Auld Fellas Ltd.";
	

	public Quotation generateQuotation(RoomInfo roomInfo) {
		// Create an initial base price based on the room type
		double basePrice;
		switch (roomInfo.type) {
			case "Single":
				basePrice = 80;
				break;
			case "Double":
				basePrice = 120;
				break;
			case "Twin":
				basePrice = 100;
				break;
			case "Suite":
				basePrice = 200;
				break;
			default:
				basePrice = 100;
				break;
		}
	
		// Add additional costs based on room attributes
		double extraCosts = 0;
		if (roomInfo.balcony) {
			extraCosts += 20;
		}
		if (roomInfo.view.equals("Sea View")) {
			extraCosts += 30;
		} else if (roomInfo.view.equals("Garden View")) {
			extraCosts += 15;
		}
	
		// Calculate the price based on the base price and extra costs
		double price = basePrice + extraCosts;
	
		// Generate the quotation and send it back
		return new Quotation(COMPANY, generateReference(PREFIX), price);
	}
	
}
