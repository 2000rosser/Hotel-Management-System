package service.controllers;

import java.util.Map;
import java.util.TreeMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import service.core.Quotation;
import service.core.RoomInfo;
import service.service.HotelService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class HotelController {
    private Map<String, Quotation> quotations = new TreeMap<>();
    private HotelService service;

    @Autowired
    public HotelController(HotelService service) {
        this.service = service;
    }

    @GetMapping(value = "/quotations", produces = "application/json")
    public ResponseEntity<ArrayList<String>> getQuotations() {
        ArrayList<String> list = new ArrayList<>();
        for (Quotation quotation : quotations.values()) {
            list.add("http:" + getHost()
                    + "/quotations/" + quotation.reference);
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping(value = "/quotations/{id}", produces = { "application/json" })
    public ResponseEntity<Quotation> getQuotation(@PathVariable String id) {
        Quotation quotation = quotations.get(id);
        if (quotation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(quotation);
    }

    @PostMapping(value = "/quotations", consumes = "application/json")
    public ResponseEntity<Quotation> createQuotation(
            @RequestBody RoomInfo info) {
        System.out.println("Service object: " + service);
        System.out.println("RoomInfo object: " + info);
        Quotation quotation = service.generateQuotation(info);
        System.out.println("Quotation object: " + quotation);
        quotations.put(quotation.reference, quotation);
        String url = "http://" + getHost() + "/quotations/"
                + quotation.reference;
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", url)
                .header("Content-Location", url)
                .body(quotation);
    }
    

    @Value("${server.port}")
    private int port;

    private String getHost() {
        try {
            return InetAddress.getLocalHost().getHostAddress() + ":" + port;
        } catch (UnknownHostException e) {
            return "localhost:" + port;
        }
    }

}
