package service.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.model.Room;
import service.service.RoomService;

@RestController  
public class RoomController {
    
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
        System.out.println("RoomController is created, roomService is: " + roomService);
    }

    @Autowired  
    RoomService roomService;

    @GetMapping("/room")  
    private List<Room> getAllRooms() {  
        return roomService.getAllRooms();
    }

    @GetMapping("/room/{id}")  
    private Room getRoom(@PathVariable("id") int id) {  
        return roomService.getRoomById(id);  
    }

    @DeleteMapping("/room/{id}")  
    private void deleteRoom(@PathVariable("id") int id) {  
        roomService.delete(id);  
    }

    @PostMapping("/room")  
    private int saveRoom(@RequestBody Room room) {  
        roomService.saveOrUpdate(room);  
        return room.getId();
    }
}
