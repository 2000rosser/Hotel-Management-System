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
import service.model.Bookings;

@RestController  
public class BookingsController {
    
    public BookingsController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
        System.out.println("RoomController is created, roomService is: " + bookingsService);
    }

    @Autowired  
    BookingsService bookingsService;

    @GetMapping("/bookings")  
    private List<Bookings> getAllBookings() {  
        return bookingsService.getAllBookings();
    }

    @GetMapping("/bookings/{id}")  
    private Bookings getRoom(@PathVariable("id") int id) {  
        return bookingsService.getBookingById(id);  
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
}
