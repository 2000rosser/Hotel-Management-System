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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;



@RestController
@CrossOrigin(origins = "*")
public class AdminController {


    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping(value = "/rooms", produces = "application/json") 
    public ResponseEntity<List<RoomInfo>> getRooms() {

        ResponseEntity<List<RoomInfo>> response = restTemplate.exchange(
            "http://hotel:8080/room",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<RoomInfo>>() {}
        );

        System.out.println("Received a request to retrieve all rooms");
        System.out.println("Returning " + response.getBody().size() + " rooms");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response.getBody());
    }

    @GetMapping(value = "/bookings", produces = "application/json") 
    public ResponseEntity<List<BookingInfo>> getBookings() {

        ResponseEntity<List<BookingInfo>> response = restTemplate.exchange(
            "http://hotel:8080/bookings",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<BookingInfo>>() {}
        );

        System.out.println("Received a request to retrieve all bookings");
        System.out.println("Returning " + response.getBody().size() + " bookings");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response.getBody());
    }

    @GetMapping(value = "/bookings/{ref}", produces = "application/json") 
    public ResponseEntity<BookingInfo> getSpecificBooking(@PathVariable("ref") int ref) {

        ResponseEntity<BookingInfo> response = restTemplate.exchange(
            "http://hotel:8080/bookings/ref/{ref}",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<BookingInfo>() {},
            ref // pass the ref here
        );

        System.out.println("Received a request to retrieve a specific booking");
        System.out.println("Returning booking");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response.getBody());
    }

    @PostMapping(value = "/bookings", consumes = "application/json", produces = "application/json")
    public ResponseEntity<BookingInfo> retrieveBooking(
            @RequestBody Checkout checkInfo) {
        System.out.println("Received a request to retrieve a booking");
        // System.out.println("Creating application: " + application.id);
        // applications.put(application.id, application);
        BookingInfo retrieved = new BookingInfo();
        ResponseEntity<BookingInfo> response = restTemplate.postForEntity("http://hotel:8080/checkout", checkInfo, BookingInfo.class);
        if(response.getStatusCode().equals(HttpStatus.OK)){
            retrieved = response.getBody();
            System.out.println("Booking recieved" + retrieved);
        }else{
            System.out.println("Error " + response.getStatusCode());
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(retrieved);

       
    }

    @PostMapping(value = "/checkout", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Checkout> performCheckout(@RequestBody Checkout checkInfo) {
        System.out.println("Received a request to retrieve a booking");
        Checkout confirmation = new Checkout();
        System.out.println("Deleting booking: " + checkInfo.id);
        System.out.println("check name" + checkInfo.name);
        System.out.println("check email" + checkInfo.email);
        HttpEntity<Integer> entity = new HttpEntity<>(checkInfo.id);
        ResponseEntity<String> response = restTemplate.exchange("http://hotel:8080/bookings/" + checkInfo.id, HttpMethod.DELETE, entity, String.class);

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

    // @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    // public ResponseEntity<String> login(@RequestBody String loginInfo) {
    //     System.out.println("Received a request to login");
    //     System.out.println("login info" + loginInfo);
    //     String confirmation = "";
    //     HttpEntity<String> entity = new HttpEntity<>(loginInfo);
    //     ResponseEntity<String> response = restTemplate.exchange("http://hotel:8080/login", HttpMethod.POST, entity, String.class);

    //     if(response.getStatusCode().equals(HttpStatus.OK)){
    //         confirmation = response.getBody();
    //         System.out.println("Login successful");
    //     } else {
    //         System.out.println("Error " + response.getStatusCode());
    //         return ResponseEntity.status(response.getStatusCode()).build();
    //     }
        
    //     return ResponseEntity
    //             .status(HttpStatus.CREATED)
    //             .body(confirmation);
    // }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin admin) {
        System.out.println("Received a request to login");
        System.out.println("login info" + admin);
        String confirmation = "";
        HttpEntity<Admin> entity = new HttpEntity<>(admin);
        ResponseEntity<String> response = restTemplate.exchange("http://hotel:8080/admin/check", HttpMethod.POST, entity, String.class);
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


}
