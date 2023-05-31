package service.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class QuoteResponseController {
    @PostMapping("/quoteResponse")
    @ResponseBody
    public String handleQuoteResponse(@RequestBody String responseArg) {
        // Process the response argument and generate the appropriate HTML content
        StringBuilder htmlPage = new StringBuilder();

        htmlPage.append("<!DOCTYPE html>");
        htmlPage.append("<html>");
        htmlPage.append("   <head>");
        htmlPage.append("       <title>Rooms Found</title>");
        htmlPage.append(getStylinghtml());
        htmlPage.append("   </head>");
        htmlPage.append("   <body>");
        htmlPage.append("   <div class=\"navbar\">");
        htmlPage.append("       <span class=\"navbar-title\">Astrum Distributed Systems (Hotel Management and Booking)</span>");
        htmlPage.append("   </div>");

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseArg);
            JsonNode quotationsNode = jsonNode.get("quotations");

            String[] company = new String[quotationsNode.size()];
            String[] reference = new String[quotationsNode.size()];
            double[] price = new double[quotationsNode.size()];
            int quoteCounter = 0;

            if (quotationsNode.size() == 1 && quotationsNode.get(0).get("totalPrice").asDouble() == 0.0) {
                System.out.println("\n####\nDefault Page detected####\n");
                htmlPage.append(
                      "<form>"
                    + "     <h2>No rooms available matching the search criteria</h2><hr><br><br>\n"
                    + "     <button onclick=\"redirectBack()\">Back to Room Options</button>\n"
                    + "</form>"
                    + "<script>\n"
                    + "   function redirectBack() {\n"
                    + "       window.location.href = \"http://localhost:8084/roomInfo.html\"\n"
                    + "   } \n"
                    + "</script>\n"
                );
            } else {
                for (JsonNode quotationNode : quotationsNode) {
                    company[quoteCounter] = quotationNode.get("company").asText();
                    reference[quoteCounter] = quotationNode.get("reference").asText();
                    price[quoteCounter] = quotationNode.get("totalPrice").asDouble();

                    JsonNode roomInfo = quotationNode.get("roomInfo");

                    htmlPage.append(
                        "       <form action=\"/payments\" method=\"get\">\n"
                        + "           <h2>Booking</h2><hr><br><br>"
                        + "           <p>Company: " + company[quoteCounter] + "</p>"
                        + "           <p>Reference: " + reference[quoteCounter] + "</p>"
                        + "           <p>Price: " + price[quoteCounter] + "</p>"
                        + "           <div class=\"two-column-grid\">"
                        + "               <div>"
                        + "                   <p>Hotel: " + roomInfo.get("hotel").asText() + "</p>"
                        + "                   <p>" + roomInfo.get("type").asText() + " Room</p>"
                        + "                   <p>" + roomInfo.get("view").asText() + "</p>"
                        + "                   <p>Check-In: " + roomInfo.get("checkIn").asText() + "</p>"
                        + "               </div>"
                        + "               <div>"
                        + "                   <p>Balcony: " + roomInfo.get("balcony").asBoolean() + "</p>"
                        + "                   <p>Accessibility: " + roomInfo.get("accessibility").asBoolean() + "</p>"
                        + "                   <p>Check-Out: " + roomInfo.get("checkOut").asText() + "</p>"
                        + "               </div>"
                        + "           </div><br>"
                        + "          <input type=\"hidden\" name=\"responseArg\" value=\'" + quotationNode + "\'/>\n"
                        + "          <input type=\"submit\" value=\"Book This Room\"/>\n"
                        + "       </form>\n"
                        + "       <br></br>"
                    );

                    quoteCounter++;
                }
            }
        }
        catch (Exception e) {
            System.out.println("\n");
            System.out.println("Error processing quote response. Raw response:\n" + responseArg);
            e.printStackTrace();

            htmlPage.append(
                "       <h1>Error</h1>\n" +
                "       <p>An error occurred while processing the quote response.</p>\n" +
                "   </body>\n" +
                "</html>"
            );
        }
        
        htmlPage.append(
            "   </body>\n" +
            "</html>"
        );

        return htmlPage.toString();
    }

    private StringBuilder getStylinghtml() {
        StringBuilder htmlPage = new StringBuilder();

        htmlPage.append(
            "            <style>"
            + "    body {"
            + "        font-family: Arial, sans-serif;"
            + "        background-image: url(hotel_blur.png);"
            + "        background-repeat: no-repeat;"
            + "        background-size: cover;"
            + "        background-attachment: fixed;"
            + "        margin: 0;"
            + "        padding: 0;"
            + "    }"

            + "    .navbar {"
            + "        background-color: #68c4e2;"
            + "        padding: 1%;"
            + "        color: #fff;"
            + "        margin: 0;"
            + "    }"

            + "    .navbar-title {"
            + "        font-size: 20px;"
            + "        font-weight: bold;"
            + "        text-align: left;"
            + "        padding: 20px;"
            + "        margin: 0;"
            + "    }"

            + "    /* Rest of the code... */"
            + "    form {"
            + "        max-width: 400px;"
            + "        margin: 2.5% 0 0 2.5%;"
            + "        background-color: white;"
            + "        padding: 20px;"
            + "    }"

            + "    label {"
            + "        display: block;"
            + "        margin-bottom: 5px;"
            + "        font-weight: bold;"
            + "    }"

            + "    select,"
            + "    input[type=\"checkbox\"],"
            + "    input[type=\"date\"],"
            + "    input[type=\"number\"],"
            + "    input[type=\"submit\"] {"
            + "        margin-bottom: 10px;"
            + "    }"

            + "    input[type=\"submit\"] {"
            + "        padding: 10px 20px;"
            + "        background-color: #68c4e2;"
            + "        color: #fff;"
            + "        border: none;"
            + "        cursor: pointer;"
            + "    }"

            + "    input[type=\"submit\"]:hover {"
            + "        background-color: #17a9da;"
            + "    }"

            + "    button {"
            + "        padding: 10px 20px;"
            + "        background-color: #68c4e2;"
            + "        color: #fff;"
            + "        border: none;"
            + "        cursor: pointer;"
            + "    }"

            + "    button:hover {"
            + "        background-color: #17a9da;"
            + "    }"

            + "    .carousel-container {"
            + "        position: absolute;"
            + "        top: 70%;"
            + "        left: 70%;"
            + "        transform: translate(-50%, -50%);"
            + "        height: 60%;"
            + "        width: 60%;"
            + "        overflow: hidden;"
            + "    }"

            + "    .carousel-item {"
            + "        position: absolute;"
            + "        top: 0;"
            + "        left: 10%; /* Adjust the left position as per your preference */"
            + "        display: none;"
            + "        padding: 10px;"
            + "        color: black;"
            + "        font-size: 24px;"
            + "        text-align: center;"
            + "        width: 80%; /* Adjust the width as per your preference */"
            + "        height: 100%;"
            + "        animation-duration: 2s;"
            + "        animation-fill-mode: forwards;"
            + "    }"

            + "    /* Animation keyframes for the carousel */"
            + "    @keyframes fade-in {"
            + "        0% {"
            + "            opacity: 0;"
            + "        }"
            + "        100% {"
            + "            opacity: 1;"
            + "        }"
            + "    }"

            + "    @keyframes fade-out {"
            + "        0% {"
            + "            opacity: 1;"
            + "        }"
            + "        100% {"
            + "            opacity: 0;"
            + "        }"
            + "    }"
            
            + "    .two-column-grid {"
            + "         display: grid;"
            + "         grid-template-columns: auto auto;"
            + "         grid-gap: 10px;"
            + "     }"

            + "    .two-column-grid p {"
            + "         line-height: 0.5;"
            + "     }"
            + "</style>"
        );

        return htmlPage;
    }
}