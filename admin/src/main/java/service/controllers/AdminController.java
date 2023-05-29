package service.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.http.HttpStatus;



@Controller
public class AdminController {

    private final RestTemplate restTemplate;

    public AdminController() {
        this.restTemplate = new RestTemplate();
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        System.out.println("Sending a request to login");
        HttpEntity<Map<String, String>> request = new HttpEntity<>(map, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity("http://hms:8083/login", request, String.class);
            System.out.println(response.getStatusCode());
            System.out.println("Received a request to login");
            
            if (response.getStatusCode().is2xxSuccessful()) {
                return "redirect:/admin-panel.html";
            } else {
                return "redirect:/login.html";
            }

        } catch(HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                return "redirect:/login.html";
            } else {
                throw ex;
            }
        }
    }


}
