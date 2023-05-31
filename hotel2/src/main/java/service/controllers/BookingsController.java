package service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.service.BookingsService;
import service.service.HotelService;
import service.core.BookingInfo;
import service.core.Checkout;
import service.model.Bookings;
import java.util.ArrayList;

@RestController  
public class BookingsController {
    
    public BookingsController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
        System.out.println("RoomController is created, roomService is: " + bookingsService);
    }

    @Autowired  
    BookingsService bookingsService;

    @Autowired
    HotelService hotelService;

    @GetMapping("/bookings")  
    private List<BookingInfo> getAllBookings() {  
        List<Bookings> bookings = bookingsService.getAllBookings();
        List<BookingInfo> bookingInfos = new ArrayList<BookingInfo>();

        for (Bookings booking : bookings) {
            BookingInfo bookingInfo = new BookingInfo();
            bookingInfo.ID = booking.getId();
            bookingInfo.name = booking.getName();
            bookingInfo.email = booking.getEmail();
            bookingInfo.phone = booking.getPhone();
            bookingInfo.booking_ref = booking.getBookingRef();
            bookingInfo.type = booking.getType();
            bookingInfo.beds = booking.getBeds();
            bookingInfo.bedSize = booking.getBedSize();
            bookingInfo.balcony = booking.isBalcony();
            bookingInfo.view = booking.getView();
            bookingInfo.accessibility = booking.isAccessible();
            bookingInfo.checkIn = booking.getCheckInDate();
            bookingInfo.checkOut = booking.getCheckOutDate();
            bookingInfo.price = booking.getPrice();
            bookingInfo.hotel = booking.getHotel();
            bookingInfos.add(bookingInfo);
        }

        return bookingInfos;
    }

    @GetMapping("/bookings/{id}")  
    private Bookings getBooking(@PathVariable("id") int id) {  
        return bookingsService.getBookingById(id);  
    }

    @GetMapping("/bookings/ref/{ref}")  
    private BookingInfo getBookingByRef(@PathVariable("ref") int ref) {  
        BookingInfo bookingInfo = new BookingInfo();
        Bookings booking = bookingsService.getBookingByBookingRef(ref);
        bookingInfo.ID = booking.getId();
        bookingInfo.name = booking.getName();
        bookingInfo.email = booking.getEmail();
        bookingInfo.phone = booking.getPhone();
        bookingInfo.booking_ref = booking.getBookingRef();
        bookingInfo.type = booking.getType();
        bookingInfo.beds = booking.getBeds();
        bookingInfo.bedSize = booking.getBedSize();
        bookingInfo.balcony = booking.isBalcony();
        bookingInfo.view = booking.getView();
        bookingInfo.accessibility = booking.isAccessible();
        bookingInfo.checkIn = booking.getCheckInDate();
        bookingInfo.checkOut = booking.getCheckOutDate();
        bookingInfo.price = booking.getPrice();
        bookingInfo.hotel = booking.getHotel();

        return bookingInfo;  
    }

    @DeleteMapping("/bookings/ref/{ref}")  
    private void deleteBookingByRef(@PathVariable("ref") int ref) {  
        bookingsService.deleteByBookingRef(ref);
    }
    

    @DeleteMapping("/bookings/{id}")  
    private void deleteBooking(@PathVariable("id") int id) {  
        bookingsService.delete(id);  
    }

    @PostMapping("/bookings")  
    private int saveBooking(@RequestBody Bookings booking) {  
        System.out.println("Entered controller - saveBooking");
        bookingsService.saveOrUpdate(booking);  
        return booking.getId();
    }

    @PostMapping("/checkout")
    private BookingInfo checkout(@RequestBody Checkout booking) {
        return hotelService.checkout(booking);
    }
}
