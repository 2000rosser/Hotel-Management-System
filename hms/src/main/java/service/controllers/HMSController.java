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
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/applications")
public class HMSController {

    private static int COUNTER = 1000;
    private Map<Integer, Application> applications = new TreeMap<>();
    private RestTemplate restTemplate = new RestTemplate();

    private List<String> quotationUrls = new ArrayList<>(); // add a list to store the URL

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Application> createApplication(
            @RequestBody RoomInfo roomInfo) {
        Application application = new Application(roomInfo);
        System.out.println("Creating application: " + application.id);
        applications.put(application.id, application);

        String url = "http://localhost:8080/quotations"; 
        //List<String> quotationUrls = getQuotationUrls();
        for (String quotationUrl : quotationUrls) {
            System.out.println("Requesting quotation from " + quotationUrl);
            ResponseEntity<Quotation> response = restTemplate.postForEntity(quotationUrl, roomInfo, Quotation.class);
            if (response.getStatusCode().equals(HttpStatus.CREATED)) {
                application.quotations.add(response.getBody());
            }else{
                System.out.println("Error requesting quotation from " + quotationUrl);
            }
        }
        // System.out.println("Requesting quotation from room booking service");
        // ResponseEntity<Quotation> response = restTemplate.postForEntity(url, roomInfo, Quotation.class);
        // if (response.getStatusCode().equals(HttpStatus.CREATED)) {
        //     application.quotations.add(response.getBody());
        // }else{
        //     System.out.println("Error requesting quotation from " + url);
        // }
        

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(application);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Application>> getApplications() {
        List<Application> applicationList = new ArrayList<>(applications.values());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(applicationList);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Application> getApplication(@PathVariable int id) {
        Application application = applications.get(id);
        if (application != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(application);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    // private List<String> getQuotationUrls() {
    //     //replace with a registration service later
    //     List<String> urls = new ArrayList<>();
    //     urls.add("http://localhost:8080/quotations");
    //     urls.add("http://localhost:8081/quotations");
    //     urls.add("http://localhost:8082/quotations");
    //     return urls;
    // }

    

    @PostMapping(value = "/services", consumes = "text/plain")
    public ResponseEntity<String> addService(@RequestBody String url) {
        if (!quotationUrls.contains(url)) {
            quotationUrls.add(url);
            System.out.println("Added new URL: " + url);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/services", produces = "application/json")
    public ResponseEntity<List<String>> getServices() {
        return ResponseEntity.ok(quotationUrls);
    }



    @Value("${server.port}")
    private int port;

    private String getHost() {
        return "localhost:" + port;
    }
}
