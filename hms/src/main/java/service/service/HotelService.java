package service.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HotelService {

    private Map<String, String> hotelUrls = new HashMap<String, String>(){{
        put("Hotel1", "http://hotel:8080");
        put("Hotel2", "http://hotel2:8081");
    }};


    public Map<String, String> getHotelUrls() {
        return hotelUrls;
    }

    

}
