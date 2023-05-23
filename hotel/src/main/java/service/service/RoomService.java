package service.service;  

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.model.Room;
import service.repository.RoomRepository;

@Service
public class RoomService {

    @Autowired  
    RoomRepository roomRepository;

    public List<Room> getAllRooms() {  
        List<Room> rooms = new ArrayList<Room>();
        roomRepository.findAll().forEach(room -> rooms.add(room));
        return rooms;
    }

    public Room getRoomById(int id) {  
        return roomRepository.findById(id).get();
    }

    public void saveOrUpdate(Room room) {  
        roomRepository.save(room);
    }

    public void delete(int id) {  
        roomRepository.deleteById(id);
    }
}
