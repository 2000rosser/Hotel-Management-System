package service.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class QuoteResponseController {
    @GetMapping("/quoteResponse")
    @ResponseBody
    public String handleQuoteResponse(@RequestParam("arg") String responseArg) {
        // Process the response argument and generate the appropriate HTML content
        StringBuilder htmlPage = new StringBuilder();

        htmlPage.append("<!DOCTYPE html>");
        htmlPage.append("<html>");
        htmlPage.append("   <head>");
        htmlPage.append("       <title>Rooms Found</title>");
        htmlPage.append(getStylinghtml());
        htmlPage.append("   </head>");
        htmlPage.append("   <body>");

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
                htmlPage.append("<h1>No rooms available matching the search criteria</h1>\n"
                    + "<button onclick=\"redirectBack()\">Back to Room Options</button>\n"
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

                    htmlPage.append(
                        "       <form action=\"/payments\" method=\"get\">\n"
                        + "           <h3>Booking</h3>"
                        + "           <p>Company: " + company[quoteCounter] + "</p>"
                        + "           <p>Reference: " + reference[quoteCounter] + "</p>"
                        + "           <p>Price: " + price[quoteCounter] + "</p>"
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
            + "            body {"
            + "                font-family: Arial, sans-serif;"
            + "                background-image: url(hotel.jpg);"
            + "                background-position: right;"
            + "                background-repeat: no-repeat;"
            + "                background-size: cover;"
            + "            }"

            + "            .navbar {"
            + "                background-color: #E8F2F7;"
            + "                padding: 10px;"
            + "                color: #333;"
            + "            }"

            + "            .navbar-title {"
            + "                font-size: 20px;"
            + "                font-weight: bold;"
            + "                text-align: left;"
            + "            }"

            + "            form {"
            + "                max-width: 400px;"
            + "                margin: 2.5% 0 0 2.5%;"
            + "                background-color: white;"
            + "                padding: 20px;"
            + "            }"

            + "            label {"
            + "                display: block;"
            + "                margin-bottom: 5px;"
            + "                font-weight: bold;"
            + "            }"

            + "            select,"
            + "            input[type=\"checkbox\"],"
            + "            input[type=\"date\"],"
            + "            input[type=\"number\"],"
            + "            input[type=\"submit\"] {"
            + "                margin-bottom: 10px;"
            + "            }"

            + "            input[type=\"submit\"] {"
            + "                padding: 10px 20px;"
            + "                background-color: #4CAF50;"
            + "                color: #fff;"
            + "                border: none;"
            + "                cursor: pointer;"
            + "            }"

            + "            input[type=\"submit\"]:hover {"
            + "                background-color: #45a049;"
            + "            }"
            + "        </style>"
        );

        return htmlPage;
    }
}