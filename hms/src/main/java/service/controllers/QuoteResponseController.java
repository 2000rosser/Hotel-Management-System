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
        }
        catch (Exception e) {
            System.out.println("\n");
            e.printStackTrace();
            System.out.println("QuoteResponseController Exception!");
            System.out.println("\n");

            htmlPage.append("<h1>Error</h1>");
            htmlPage.append("<p>An error occurred while processing the quote response.</p>");
            htmlPage.append("<p>" + responseArg + "</p>");
        }

        htmlPage.append("</body>");
        htmlPage.append("</html>");

        return htmlPage.toString();
    }
}