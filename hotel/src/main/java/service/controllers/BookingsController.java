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
    private List<Bookings> getAllBookings() {  
        return bookingsService.getAllBookings();
    }

    @GetMapping("/bookings/{id}")  
    private Bookings getBooking(@PathVariable("id") int id) {  
        return bookingsService.getBookingById(id);  
    }

    @GetMapping("/bookings/ref/{ref}")  
    private Bookings getBookingByRef(@PathVariable("ref") int ref) {  
        return bookingsService.getBookingByBookingRef(ref);  
    }

    @DeleteMapping("/bookings/ref/{ref}")  
    private void deleteBookingByRef(@PathVariable("ref") int ref) {  
        bookingsService.deleteByBookingRef(ref);;  
    }
    

    @DeleteMapping("/bookings/{id}")  
    private void deleteBooking(@PathVariable("id") int id) {  
        bookingsService.delete(id);  
    }

    @PostMapping("/bookings")  
    private int saveBooking(@RequestBody Bookings booking) {  
        bookingsService.saveOrUpdate(booking);  
        return booking.getId();
    }

    @PostMapping("/checkout")
    private BookingInfo checkout(@RequestBody Checkout booking) {
        return hotelService.checkout(booking);
    }
}
