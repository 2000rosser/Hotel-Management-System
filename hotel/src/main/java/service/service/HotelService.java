package service.service;

import service.core.AbstractQuotationService;
import service.core.BookingInfo;
import service.core.RoomInfo;
import service.core.Quotation;
import service.model.Bookings;

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
    public HotelService(RoomService roomService, BookingsService bookingService) {
		this.roomService = roomService;
		this.bookingsService = bookingService;
		System.out.println("HotelService is created, roomService is: " + roomService);
    }

	private BookingsService bookingsService;

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

		Bookings booking = new Bookings();

		booking.setBookingRef(info.booking_ref);
		booking.setId(info.ID);
		booking.setType(info.type);
		booking.setBeds(info.beds);
		booking.setBedSize(info.bedSize);
		booking.setBalcony(info.balcony);
		booking.setView(info.view);
		booking.setAccessible(info.accessibility);
		booking.setCheckInDate(info.checkIn);
		booking.setCheckOutDate(info.checkOut);
		booking.setPrice(info.price);
		booking.setBooked(true);


		bookingsService.saveOrUpdate(booking);		

		return info;
	}
	
}
