package service.service;

import service.core.AbstractQuotationService;
import service.core.BookingInfo;
import service.core.RoomInfo;
import service.core.Quotation;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import service.model.Room;




@Service
public class HotelService extends AbstractQuotationService {
	// All references are to be prefixed with an AF (e.g. AF001000)
	public static final String PREFIX = "AF";
	public static final String COMPANY = "Auld Fellas Ltd.";
	public double totalPrice = 0;

	

	@Autowired
    public HotelService(RoomService roomService) {
		this.roomService = roomService;
		System.out.println("HotelService is created, roomService is: " + roomService);
    }


	private RoomService roomService;

	public Quotation generateQuotation(RoomInfo roomInfo) {


	
		if (roomInfo == null) {
			throw new IllegalArgumentException("RoomInfo cannot be null.");
		}

		List<Room> rooms = roomService.getAllRooms();

		if (rooms == null) {
			throw new IllegalArgumentException("Rooms cannot be null.");
		}
		for (Room room : rooms) {
			
			if ((roomInfo.type.equals(room.getType())) && (roomInfo.beds == room.getBeds()) && (roomInfo.bedSize == room.getBedSize()) && (roomInfo.balcony == room.isBalcony()) && (roomInfo.view.equals(room.getView())) && (roomInfo.accessibility == room.isAccessible())) {
				long daysBetween = ChronoUnit.DAYS.between(room.getCheckInDate(), room.getCheckOutDate());
				int days = Math.toIntExact(daysBetween);

				// Add additional costs based on room attributes
				double extraCosts = 0;
				if (roomInfo.balcony) {
					extraCosts += 20;
				}
				if (roomInfo.view.equals("Sea View")) {
					extraCosts += 30;
				} 
				else if (roomInfo.view.equals("Garden View")) {
					extraCosts += 15;
				}
				else if (roomInfo.view.equals("Any View")) {
					extraCosts += 0;
				}

				totalPrice = room.getPrice() * days + extraCosts;
				// System.out.println("Total Price: " + totalPrice);
				// System.out.println("Price Per Night: " + room.getPrice());
				// System.out.println("Room Type: " + room.getType());
				// System.out.println("Number of Beds: " + room.getBeds());
				// System.out.println("Bed Size: " + room.getBedType());
				// System.out.println("Balcony Included: " + room.isBalcony());
				// System.out.println("View: " + room.getView());
				// System.out.println("Accessibility: " + room.isAccessible());
				// System.out.println("Check-In Date: " + room.getCheckInDate());
				// System.out.println("Check-Out Date: " + room.getCheckOutDate());
				return new Quotation(COMPANY, generateReference(PREFIX), totalPrice, roomInfo);
			}
			else
			{
				System.out.println("NO ROOMS AVAILABLE WITH THESE CRITERIA");
			}
		}

		return null;
	}


	public BookingInfo createBooking(BookingInfo info){

		

		return info;
	}
	
}
