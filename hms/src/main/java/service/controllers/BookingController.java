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

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = "*")
public class BookingController {

    private RestTemplate restTemplate = new RestTemplate();


    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<BookingInfo> createBooking(
            @RequestBody BookingInfo bookInfo) {
        System.out.println("Received a request to create an application");
        // System.out.println("Creating application: " + application.id);
        // applications.put(application.id, application);
        BookingInfo confirmed = new BookingInfo();
        ResponseEntity<BookingInfo> response = restTemplate.postForEntity("http://hotel:8080/confirmation", bookInfo, BookingInfo.class);
        if(response.getStatusCode().equals(HttpStatus.CREATED)){
            confirmed = response.getBody();
            System.out.println("Booking confirmation recieved" + confirmed);
        }else{
            System.out.println("Error booking " + response.getStatusCode());
        }
        System.out.println("Booking created and saved. Returning booking confirmation with status CREATED.");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(confirmed);

       
    }

}
