package service.service;

import service.core.AbstractQuotationService;
import service.core.BookingInfo;
import service.core.RoomInfo;
import service.core.Quotation;
import service.core.Checkout;
import service.model.Bookings;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

	public ArrayList<Quotation> generateQuotations(RoomInfo roomInfo) {
		if (roomInfo == null) {
			throw new IllegalArgumentException("RoomInfo cannot be null.");
		}
	
		List<Room> rooms = roomService.getAllRooms();
		List<Bookings> bookedRooms = bookingsService.getAllBookings();
		ArrayList<Quotation> quotations = new ArrayList<Quotation>();
	
		if (rooms == null) {
			throw new IllegalArgumentException("Rooms cannot be null.");
		}
	
		for (Room room : rooms) {
			if ((roomInfo.type.equals(room.getType())) && (roomInfo.beds == room.getBeds()) && (roomInfo.bedSize == room.getBedSize()) && (roomInfo.balcony == room.isBalcony()) && (roomInfo.view.equals(room.getView())) && (roomInfo.accessibility == room.isAccessible())) {
				
				// Check if the checkIn date is in the past
				if (LocalDate.parse(roomInfo.checkIn).isBefore(LocalDate.now()) || LocalDate.parse(roomInfo.checkOut).isBefore(LocalDate.now())) {
					continue; // Skip to the next room
				}
				
				// Check if the checkOut date is before the checkIn date
				if (LocalDate.parse(roomInfo.checkOut).isBefore(LocalDate.parse(roomInfo.checkIn))) {
					continue; // Skip to the next room
				}
				
				long daysBetween = ChronoUnit.DAYS.between(LocalDate.parse(roomInfo.checkIn), LocalDate.parse(roomInfo.checkOut));
				int days = Math.toIntExact(daysBetween);
				boolean isOverlapping = false;
				boolean isBooked = false;
				
				// Checking for overlapping dates between user proposed dates and existing bookings
				for (Bookings booking : bookedRooms) {
					LocalDate bookedCheckIn = booking.getCheckInDate();
					LocalDate bookedCheckOut = booking.getCheckOutDate();
					
					if (!(LocalDate.parse(roomInfo.checkOut).isBefore(bookedCheckIn) || LocalDate.parse(roomInfo.checkIn).isAfter(bookedCheckOut))) {
						System.out.println("******************************************OVERLAP******************************************");
						isOverlapping = true;

						// Checking for current room booking status
						if(booking.isBooked() == true)
						{
							isBooked = true;
						}
					}
				}

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
				else if (roomInfo.view.equals("City View")) {
					extraCosts += 0;
				}

				// Setting the CheckIn and CheckOut Dates for the Quote usng Client specified dates
				room.setCheckInDate(LocalDate.parse(roomInfo.checkIn));
				room.setCheckInDate(LocalDate.parse(roomInfo.checkOut));
				
				totalPrice = (room.getPrice() * days) + (extraCosts * days);
				Quotation quote = new Quotation(COMPANY, generateReference(PREFIX), totalPrice, roomInfo, room.getId());

				if(isOverlapping == false && isBooked == false)
				{
					quotations.add(quote);
				}

			} else if ((roomInfo.type.equals(room.getType())) && (roomInfo.beds == room.getBeds()) && (roomInfo.accessibility == room.isAccessible())) {

				// Check if the checkIn date is in the past
				if (LocalDate.parse(roomInfo.checkIn).isBefore(LocalDate.now()) || LocalDate.parse(roomInfo.checkOut).isBefore(LocalDate.now())) {
					continue; // Skip to the next room
				}
				
				// Check if the checkOut date is before the checkIn date
				if (LocalDate.parse(roomInfo.checkOut).isBefore(LocalDate.parse(roomInfo.checkIn))) {
					continue; // Skip to the next room
				}

				long daysBetween = ChronoUnit.DAYS.between(LocalDate.parse(roomInfo.checkIn), LocalDate.parse(roomInfo.checkOut));
				int days = Math.toIntExact(daysBetween);
				boolean isOverlapping = false;
				boolean isBooked = false;
				
				// Checking for overlapping dates between user proposed dates and existing bookings
				for (Bookings booking : bookedRooms) {
					LocalDate bookedCheckIn = booking.getCheckInDate();
					LocalDate bookedCheckOut = booking.getCheckOutDate();
					
					if (!(LocalDate.parse(roomInfo.checkOut).isBefore(bookedCheckIn) || LocalDate.parse(roomInfo.checkIn).isAfter(bookedCheckOut))) {
						System.out.println("******************************************OVERLAP******************************************");
						isOverlapping = true;

						// Checking for current room booking status
						if(booking.isBooked() == true)
						{
							isBooked = true;
						}
					}
				}

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
				else if (roomInfo.view.equals("City View")) {
					extraCosts += 0;
				}

				// Setting the CheckIn and CheckOut Dates for the Quote usng Client specified dates
				room.setCheckInDate(LocalDate.parse(roomInfo.checkIn));
				room.setCheckInDate(LocalDate.parse(roomInfo.checkOut));
				
				totalPrice = (room.getPrice() * days) + (extraCosts * days);
				Quotation quote = new Quotation(COMPANY, generateReference(PREFIX), totalPrice, roomInfo, room.getId());

				if(isOverlapping == false && isBooked == false)
				{
					quotations.add(quote);
				}
			}
		}
	
		// If no matching room is found, return a Quotation for the requested RoomInfo.
		if (quotations.isEmpty()) {
			System.out.println("NO ROOMS AVAILABLE WITH THESE CRITERIA, CREATING A NEW QUOTATION");
			// Assume a default price and room ID for the non-existent room.
			int defaultRoomID = 0;
			double defaultPrice = 0.0;
			quotations.add(new Quotation(COMPANY, generateReference(PREFIX), defaultPrice, roomInfo, defaultRoomID));
		}
			System.out.println(quotations.size());
			return quotations;
		}

		public BookingInfo checkout(Checkout checkInfo){
		List<Bookings> bookings = bookingsService.getAllBookings();

		if (bookings == null) {
			return null;
		}

		for (Bookings booking : bookings) {
			System.out.println("Booking name: " + booking.getName() + " BookingInfo name: " + checkInfo.name);
			System.out.println("Booking email: " + booking.getEmail() + " BookingInfo email: " + checkInfo.email);
			System.out.println("Booking ID: " + booking.getId() + " BookingInfo ID: " + checkInfo.id);
			if (booking.getName().equals(checkInfo.name) && booking.getEmail().equals(checkInfo.email) && booking.getId()==checkInfo.id) {
				
				
				BookingInfo info = new BookingInfo();
				info.ID = booking.getId();
				info.name = booking.getName();
				info.email = booking.getEmail();
				info.phone = booking.getPhone();
				info.booking_ref = booking.getBookingRef();
				info.type = booking.getType();
				info.beds = booking.getBeds();
				info.bedSize = booking.getBedSize();
				info.balcony = booking.isBalcony();
				info.view = booking.getView();
				info.accessibility = booking.isAccessible();
				info.checkIn = booking.getCheckInDate();
				info.checkOut = booking.getCheckOutDate();
				info.price = booking.getPrice();

				// bookingsService.delete(booking.getId());
				
				return info;
			}
		}
		return null;
		
	}
	

	public BookingInfo createBooking(BookingInfo info){

		Bookings booking = new Bookings();

		booking.setBookingRef(info.booking_ref);
		booking.setId(info.ID);
		// booking.setName(info.name);
		// booking.setEmail(info.email);
		booking.setName("test");
		booking.setEmail("test");
		booking.setPhone(info.phone);
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