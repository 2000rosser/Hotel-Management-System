package service.service;  

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.model.Bookings;
import service.repository.BookingsRepository;
import javax.transaction.Transactional;


@Service
public class BookingsService {

    @Autowired  
    BookingsRepository bookingsRepository;

    public List<Bookings> getAllBookings() {  
        List<Bookings> bookings = new ArrayList<Bookings>();
        bookingsRepository.findAll().forEach(booking -> bookings.add(booking));
        return bookings;
    }

    public Bookings getBookingByBookingRef(int booking_ref) {  
        return bookingsRepository.findByBookingRef(booking_ref);
    }

    public void deleteByBookingRef(int booking_ref) {  
        bookingsRepository.deleteByBookingRef(booking_ref);
    }

    public Bookings getBookingById(int id) {  
        return bookingsRepository.findById(id).get();
    }

    public void saveOrUpdate(Bookings booking) {  
        bookingsRepository.save(booking);
        System.out.println("booking name: " + booking.getName());
    }

    @Transactional
    public void delete(int id) {  
        bookingsRepository.deleteById(id);
    }

    public int getTableCount() {
        return (int) bookingsRepository.count();
    }
}
