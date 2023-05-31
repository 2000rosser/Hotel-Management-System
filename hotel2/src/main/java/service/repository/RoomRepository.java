package service.repository;  

import org.springframework.data.repository.CrudRepository;
import service.model.Room;

public interface RoomRepository extends CrudRepository<Room, Integer> {
}
