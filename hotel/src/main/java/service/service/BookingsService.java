package service.service;  

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.model.Bookings;
import service.repository.BookingsRepository;


@Service
public class BookingsService {

    @Autowired  
    BookingsRepository bookingsRepository;

    public List<Bookings> getAllBookings() {  
        List<Bookings> bookings = new ArrayList<Bookings>();
        bookingsRepository.findAll().forEach(booking -> bookings.add(booking));
        return bookings;
    }

    public Bookings getBookingById(int id) {  
        return bookingsRepository.findById(id).get();
    }

    public void saveOrUpdate(Bookings booking) {  
        bookingsRepository.save(booking);
    }

    public void delete(int id) {  
        bookingsRepository.deleteById(id);
    }
}
