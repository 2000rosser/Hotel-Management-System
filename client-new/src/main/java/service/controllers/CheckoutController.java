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
import service.core.BookInfo;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<BookInfo> confirmBooking(
            @RequestBody BookInfo bookInfo) {
                System.out.println("Confirming booking: " + bookInfo);

                

                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(bookInfo);

            }
}