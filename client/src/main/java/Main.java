import service.core.RoomInfo;
import service.core.Quotation;
import service.core.Application;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    private static final String APPLICATION_URL = "http://localhost:8083/applications";

    public static void main(String[] args) {
		OkHttpClient client = new OkHttpClient();
		ObjectMapper mapper = new ObjectMapper();
	
		for (RoomInfo info : rooms) {
			String json;
			try {
				json = mapper.writeValueAsString(info);
			} catch (IOException e) {
				continue;
			}
	
			MediaType mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, json);
	
			Request request = new Request.Builder()
					.url(APPLICATION_URL)
					.post(body)
					.build();
	
			try {
				Response response = client.newCall(request).execute();
				if (response.isSuccessful()) {
					String responseJson = response.body().string();
					Application application = mapper.readValue(responseJson, Application.class);
					RoomInfo roomInfo= new RoomInfo();

					ArrayList<Quotation> quote = application.quotations;
					roomInfo = quote.get(0).roomInfo;

					System.out.println("Room Booking Quotation [" + application.id + "]");
					for (Quotation quotation : application.quotations) {
						System.out.println("Total Price: " + quotation.totalPrice);
						System.out.println("Price Per Night: " + roomInfo.price);
						System.out.println("Room Type: " + roomInfo.type);
						System.out.println("Number of Beds: " + roomInfo.beds);
						System.out.println("Bed Size: " + roomInfo.bedSize);
						System.out.println("Balcony Included: " + roomInfo.balcony);
						System.out.println("View: " + roomInfo.view);
						System.out.println("Accessibility: " + roomInfo.accessibility);
						System.out.println("Check-In Date: " + roomInfo.checkIn);
						System.out.println("Check-Out Date: " + roomInfo.checkOut);
						System.out.println("|====================================================================================|\n\n");
					}
				} else {
					System.out.println("Failed to create application: " + response.code());
				}
			} catch (IOException e) {
				System.out.println("Failed to send request to server");
			}
		}
		System.exit(0);
    }


	public static final RoomInfo[] rooms = {
		new RoomInfo("Single", 1, 1.0, false, "Sea View", false, "2023-06-01", "2023-06-03", 80)
	};
}

