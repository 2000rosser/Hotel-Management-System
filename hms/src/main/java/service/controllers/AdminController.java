package service.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import service.core.Application;
import service.core.RoomInfo;
import service.core.Quotation;
import service.core.BookingInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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


}
