package service.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import service.core.RoomInfo;
import service.core.BookingInfo;
import service.core.Checkout;
import service.core.Admin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import service.service.HotelService;


@RestController
@CrossOrigin(origins = "*")
public class AdminController {


    private RestTemplate restTemplate = new RestTemplate();
    private final HotelService hotelService;
    private Map<String, String> hotelUrls;

    @Autowired
    public AdminController(HotelService hotelService) {
        this.hotelService = hotelService;
        this.hotelUrls = hotelService.getHotelUrls();
    }

    @GetMapping(value = "/rooms", produces = "application/json") 
    public ResponseEntity<List<RoomInfo>> getRooms() {

        List<RoomInfo> allRooms = new ArrayList<>();

        for (Map.Entry<String, String> entry : hotelUrls.entrySet()) {
            ResponseEntity<List<RoomInfo>> response = restTemplate.exchange(
                entry.getValue() + "/room",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RoomInfo>>() {}
            );

            allRooms.addAll(response.getBody());
        }



        System.out.println("Received a request to retrieve all rooms");
        System.out.println("Returning " + allRooms.size() + " rooms");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allRooms);
    }


    @PostMapping(value = "/rooms/add", consumes = "application/json")
    public ResponseEntity<RoomInfo> addRoom(@RequestBody RoomInfo roomInfo) {
        System.out.println("Received a request to add a room");
        System.out.println("room id" + roomInfo.id);
        System.out.println("room type" + roomInfo.type);
        System.out.println("room price" + roomInfo.price);
        System.out.println("room price" + roomInfo.hotel);

        String hotelUrl = hotelUrls.get(roomInfo.hotel);
        
        ResponseEntity<RoomInfo> response = restTemplate.postForEntity(hotelUrl + "/room/add", roomInfo, RoomInfo.class);
        RoomInfo roomWithId = response.getBody();

        System.out.println("Room added with id: " + roomWithId.id);
        System.out.println("Response status code: " + response.getStatusCode());
        
        if(response.getStatusCode().equals(HttpStatus.OK)){
            System.out.println("Room added");
        }else{
            System.out.println("Error adding room " + response.getStatusCode());
        }
        System.out.println("Room added and saved. Returning room with status CREATED.");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(roomWithId);
    }


    @GetMapping(value = "/bookings", produces = "application/json") 
    public ResponseEntity<List<BookingInfo>> getBookings() {

        List<BookingInfo> allBookings = new ArrayList<>();

        for (Map.Entry<String, String> entry : hotelUrls.entrySet()) {
            ResponseEntity<List<BookingInfo>> response = restTemplate.exchange(
                entry.getValue() + "/bookings",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BookingInfo>>() {}
            );

            allBookings.addAll(response.getBody());
        }
        

        for (BookingInfo booking : allBookings) {
            System.out.println(booking.ID);
            System.out.println(booking.name);
            System.out.println(booking.email);
        }

        System.out.println("Received a request to retrieve all bookings");
        System.out.println("Returning " + allBookings.size() + " bookings");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allBookings);
    }

    @GetMapping(value = "/bookings/{ref}", produces = "application/json") 
    public ResponseEntity<BookingInfo> getSpecificBooking(@PathVariable("ref") int ref) {

        BookingInfo allBookings = new BookingInfo();

        for (Map.Entry<String, String> entry : hotelUrls.entrySet()) {
            ResponseEntity<BookingInfo> response = restTemplate.exchange(
                entry.getValue() + "/bookings/ref/{ref}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<BookingInfo>() {},
                ref
            );

            if(response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null){
                allBookings = response.getBody();
                System.out.println("Booking recieved" + allBookings);
            }else{
                System.out.println("Not found in hotel " + entry.getKey());
            }
        }


        System.out.println("Received a request to retrieve a specific booking");
        System.out.println("Returning booking");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allBookings);
    }


    @PostMapping(value = "/bookings", consumes = "application/json", produces = "application/json")
    public ResponseEntity<BookingInfo> retrieveBooking(
            @RequestBody Checkout checkInfo) {
        System.out.println("Received a request to retrieve a booking");
        System.out.println("check name " + checkInfo.name);
        System.out.println("check email " + checkInfo.email);
        System.out.println("check booking ref " + checkInfo.booking_ref);
        System.out.println("check hotel " + checkInfo.hotel);

        String hotelUrl = hotelUrls.get(checkInfo.hotel);


        BookingInfo retrieved = new BookingInfo();
        ResponseEntity<BookingInfo> response = restTemplate.postForEntity(hotelUrl + "/checkout", checkInfo, BookingInfo.class);
        //print out the response status code
        System.out.println("Response status code: " + response.getStatusCode());
        if(response.getStatusCode().equals(HttpStatus.OK)){
            retrieved = response.getBody();
            System.out.println("Booking recieved" + retrieved);
        }else{
            System.out.println("Error " + response.getStatusCode());
        }
        //print out the retrieved booking
        System.out.println("Retrieved booking: " + retrieved.ID);
        System.out.println("Retrieved booking: " + retrieved.name);
        System.out.println("Retrieved booking: " + retrieved.email);


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(retrieved);

       
    }

    @PostMapping(value = "/checkout", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Checkout> performCheckout(@RequestBody Checkout checkInfo) {
        System.out.println("Received a request to retrieve a booking");
        Checkout confirmation = new Checkout();
        // System.out.println("Deleting booking: " + checkInfo.id);
        System.out.println("check name " + checkInfo.name);
        System.out.println("check email " + checkInfo.email);
        System.out.println("check hotel " + checkInfo.hotel);

        String hotelUrl = hotelUrls.get(checkInfo.hotel);
        HttpEntity<Integer> entity = new HttpEntity<>(checkInfo.booking_ref);
        ResponseEntity<String> response = restTemplate.exchange(hotelUrl + "/bookings/ref/" + checkInfo.booking_ref, HttpMethod.DELETE, entity, String.class);

        if(response.getStatusCode().equals(HttpStatus.OK) || response.getStatusCode().equals(HttpStatus.NO_CONTENT)){
            confirmation = checkInfo;
            System.out.println("Booking deleted");
        } else {
            System.out.println("Error " + response.getStatusCode());
            return ResponseEntity.status(response.getStatusCode()).build();
        }
        
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(confirmation);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin admin) {
        System.out.println("Received a request to login");
        System.out.println("login info" + admin);
        String confirmation = "";
        HttpEntity<Admin> entity = new HttpEntity<>(admin);
        String hotelUrl = hotelUrls.get(admin.hotel);
        ResponseEntity<String> response = restTemplate.exchange(hotelUrl + "/admin/check", HttpMethod.POST, entity, String.class);
        System.out.println("response" + response);
        if(response.getStatusCode().equals(HttpStatus.OK) &&  response.getBody() != null){
            confirmation = response.getBody();
            System.out.println("Login successful");
            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(confirmation);
        } else {
            System.out.println("Error " + response.getStatusCode());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
    }

    @DeleteMapping("/rooms/delete/{id}/{hotel}")
    public ResponseEntity<?> deleteRoom(@PathVariable("id") int id, @PathVariable("hotel") String hotel) {
        System.out.println("Received a request to delete a room");
        System.out.println("Deleting room: " + id);
        String hotelUrl = hotelUrls.get(hotel);
        HttpEntity<Integer> entity = new HttpEntity<>(id);
        ResponseEntity<String> response = restTemplate.exchange(hotelUrl + "/room/" + id, HttpMethod.DELETE, entity, String.class);

        if(response.getStatusCode().equals(HttpStatus.OK) || response.getStatusCode().equals(HttpStatus.NO_CONTENT)){
            System.out.println("Room deleted");
        } else {
            System.out.println("Error " + response.getStatusCode());
            return ResponseEntity.status(response.getStatusCode()).build();
        }
        
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
    


}
