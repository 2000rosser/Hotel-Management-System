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
                System.out.println("Error while converting RoomInfo to JSON: " + e.getMessage());
                continue;
            }

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, json);

            Request request = new Request.Builder()
                    .url(APPLICATION_URL)
                    .post(body)
                    .build();

            try {
                System.out.println("Sending request to server...");
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    String responseJson = response.body().string();
                    Application application = mapper.readValue(responseJson, Application.class);

                    System.out.println("Booking Reference " + application.id);

					Quotation cheapestQuotation = application.quotations.get(0);
					RoomInfo cheapestRoomInfo = cheapestQuotation.roomInfo;
                    for (Quotation quotation : application.quotations) {
						System.out.println("Total Price: " + quotation.totalPrice);
                        System.out.println("Price Per Night: " + cheapestRoomInfo.price);
						System.out.println("Room Type:: " + cheapestRoomInfo.type);
						System.out.println("Number of Beds: " + cheapestRoomInfo.beds);
						System.out.println("Bed Size: " + cheapestRoomInfo.bedSize);
						System.out.println("Balcony Included: " + cheapestRoomInfo.balcony);
						System.out.println("View: " + cheapestRoomInfo.view);
						System.out.println("Accessibility: " + cheapestRoomInfo.accessibility);
						System.out.println("Check-In Date: " + cheapestRoomInfo.checkIn);
						System.out.println("Check-Out Date: " + cheapestRoomInfo.checkOut);
                        System.out.println("|============================================|\n\n\n\n");
						System.out.println("|=============================|\n\n");
						System.out.println("|=============BOOK============|\n\n");
						System.out.println("|=============================|\n\n");
                    }
                } else {
                    System.out.println("Failed to create application. Server response code: " + response.code());
                }
            } catch (IOException e) {
                System.out.println("Failed to send request to server. Exception: " + e.getMessage());
            }
        }
        System.exit(0);
    }


	public static final RoomInfo[] rooms = {
		new RoomInfo("Single", 1, 1.0, false, "Sea View", false, "2023-06-01", "2023-06-03", 80)
	};
}
