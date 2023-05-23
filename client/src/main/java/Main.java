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
	
					System.out.println("Application created with ID " + application.id);
					for (Quotation quotation : application.quotations) {
						System.out.println("price = " + quotation.price);
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
		new RoomInfo("Single", 1, 1.0, false, "City View", false, "2023-06-01", "2023-06-03", 80)
	};
}