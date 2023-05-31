package service.repository;  

import org.springframework.data.repository.CrudRepository;
import service.model.Bookings;

public interface BookingsRepository extends CrudRepository<Bookings, Integer> {
    Bookings findByBookingRef(int booking_ref);

    void deleteByBookingRef(int booking_ref);
}
