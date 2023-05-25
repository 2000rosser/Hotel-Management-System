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
        htmlPage.append("<title>Rooms Found</title>");
        htmlPage.append("</head>");
        htmlPage.append("<body>");

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseArg);
            JsonNode quotationsNode = jsonNode.get("quotations");

            String[] company = new String[quotationsNode.size()];
            String[] reference = new String[quotationsNode.size()];
            double[] price = new double[quotationsNode.size()];
            int quoteCounter = 0;

            for (JsonNode quotationNode : quotationsNode) {
                company[quoteCounter] = quotationNode.get("company").asText();
                reference[quoteCounter] = quotationNode.get("reference").asText();
                price[quoteCounter] = quotationNode.get("totalPrice").asDouble();

                htmlPage.append("<p>Company: " + company[quoteCounter] + "</p>");
                htmlPage.append("<p>Reference: " + reference[quoteCounter] + "</p>");
                htmlPage.append("<p>Price: " + price[quoteCounter] + "</p>");

                quoteCounter++;
            }
            

            return "<html>\n"
                + "    <body>\n"
                + "        <h1>Quote Response</h1>\n"
                + "        <p>" + responseArg + "</p>\n"
                + "        <p>Company: " + company[0] + "</p>\n"
                + "        <form action=\"/payments\" method=\"get\">\n"
                + "            <input type=\"hidden\" name=\"responseArg\" value=\'" + responseArg.replace("'", "&#39;") + "\'/>\n"
                + "            <input type=\"submit\" value=\"Proceed to Payment\"/>\n"
                + "        </form>\n"
                + "    </body>\n"
                + "</html>";

        }
        catch (Exception e) {
            System.out.println("\n");
            System.out.println("Error processing quote response. Raw response:\n" + responseArg);
            e.printStackTrace();
            return "<html>\n"
                    + "    <body>\n"
                    + "        <h1>Error</h1>\n"
                    + "        <p>An error occurred while processing the quote response.</p>\n"
                    + "    </body>\n"
                    + "</html>";
        }
        
    }
}