import java.text.NumberFormat;

import service.core.RoomInfo;
import service.core.Quotation;
import service.core.Application;
import java.text.NumberFormat;
import java.util.Locale;

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
						System.out.println("price =" + quotation.price);
						System.out.println("|=================================================================================================================|\n\n");
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



	// public static void displayProfile(ClientInfo info) {
	// 	System.out.println("|=================================================================================================================|");
	// 	System.out.println("|                                     |                                     |                                     |");
	// 	System.out.println(
	// 			"| Name: " + String.format("%1$-29s", info.name) + 
	// 			" | Gender: " + String.format("%1$-27s", (info.gender==ClientInfo.MALE?"Male":"Female")) +
	// 			" | Age: " + String.format("%1$-30s", info.age)+" |");
	// 	System.out.println(
	// 			"| Weight/Height: " + String.format("%1$-20s", info.weight+"kg/"+info.height+"m") + 
	// 			" | Smoker: " + String.format("%1$-27s", info.smoker?"YES":"NO") +
	// 			" | Medical Problems: " + String.format("%1$-17s", info.medicalIssues?"YES":"NO")+" |");
	// 	System.out.println("|                                     |                                     |                                     |");
	// 	System.out.println("|=================================================================================================================|");
	// }

	// /**
	//  * Display a quotation nicely - note that the assumption is that the quotation will follow
	//  * immediately after the profile (so the top of the quotation box is missing).
	//  * 
	//  * @param quotation
	//  */
	// public static void displayQuotation(Quotation quotation) {
	// 	System.out.println(
	// 			"| Company: " + String.format("%1$-26s", quotation.company) + 
	// 			" | Reference: " + String.format("%1$-24s", quotation.reference) +
	// 			" | Price: " + String.format("%1$-28s", NumberFormat.getCurrencyInstance().format(quotation.price))+" |");
	// 	System.out.println("|=================================================================================================================|");
	// }
	
	/**
	 * Test Data
	 */
	public static final RoomInfo[] rooms = {
		new RoomInfo("Single", 1, 1.0, false, "City View", false, "2023-06-01", "2023-06-03", 80),
		new RoomInfo("Double", 1, 1.5, true, "Garden View", false, "2023-06-10", "2023-06-15", 120),
		new RoomInfo("Twin", 2, 1.0, false, "Sea View", true, "2023-07-01", "2023-07-05", 100),
		new RoomInfo("Suite", 2, 2.0, true, "City View", false, "2023-07-20", "2023-07-25", 200),
		new RoomInfo("Double", 1, 1.5, false, "Garden View", true, "2023-08-01", "2023-08-10", 110),
		new RoomInfo("Twin", 2, 1.0, true, "Sea View", false, "2023-08-15", "2023-08-20", 90)
	};
	

}
