package service.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.html.HTMLParagraphElement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaymentsController {
    @GetMapping("/payments")
    @ResponseBody
    public String handlePayments(@RequestParam("responseArg") String responseArg) {
        // Process the response argument and generate the appropriate HTML content
        //{"company":"Auld Fellas Ltd.","reference":"AF001000","totalPrice":110,"roomInfo":{"type":"Single","beds":1,"bedSize":1,"balcony":false,"view":"Sea View","accessibility":false,"checkIn":"2023-05-28","checkOut":"2023-05-29","price":2},"roomId":1}
        
        String type = roomInfoElement(responseArg, "type");
        int beds = Integer.parseInt(roomInfoElement(responseArg, "beds"));
        double bedSize = Double.parseDouble(roomInfoElement(responseArg, "bedSize"));
        boolean balcony = Boolean.parseBoolean(roomInfoElement(responseArg, "balcony"));
        String view = roomInfoElement(responseArg, "view");
        boolean accessibility = Boolean.parseBoolean(roomInfoElement(responseArg, "accessibility"));
        String checkIn = roomInfoElement(responseArg, "checkIn");
        String checkOut = roomInfoElement(responseArg, "checkOut");
        double price = Double.parseDouble(rootElement( responseArg,  "totalPrice"));
        int roomId = Integer.parseInt(rootElement(responseArg, "roomId"));
        
        String bookingObject = jsonConstruct(type, beds, bedSize, balcony, view, accessibility, checkIn, checkOut, price, roomId);

        try {
            // Return an HTML form with the JSON data embedded in it
            return "<!DOCTYPE html><html>\n"
                    + "    <body>\n"
                    + "        <form id=\"paymentForm\">\n"
                    + "             <h1>Payment Page</h1>\n"

                    + "             <label for=\"name\">Name</label>"
                    + "             <input type=\"text\" id=\"name\" name=\"name\"><br><br>"

                    + "             <label for=\"email\">Email</label>"
                    + "             <input type=\"text\" id=\"email\" name=\"email\"><br><br>"

                    + "             <label for=\"phone\">Phone</label>"
                    + "             <input type=\"text\" id=\"phone\" name=\"phone\"><br><br>"

                    + "            <input type=\"hidden\" name=\"responseArg\" id=\"responseArg\" value=\'" + bookingObject + "\'/>\n"
                    + "            <input type=\"button\" value=\"Submit Payment\" onclick=\"submitPayment()\"/>\n"
                    + "        </form><br><br>\n"
                    + "        <div id=\"showConfirmation\"></div><br>\n"

                    + "        <form action=\"http://localhost:8084/roomInfo.html\">\n"
                    + "             <input type=\"submit\" value=\"Back to Room Select\">\n"
                    + "         </form><br>"

                    + "        <script>\n"
                    + "             function submitPayment() {\n"
                    + "                 var responseArg = document.getElementById('responseArg').value;\n"
                    + "                 var responseObj = JSON.parse(responseArg);\n"
                    + "                 var name = document.getElementById(\"name\").value;\n"

                    + "                 responseObj.name = document.getElementById(\"name\").value;\n"
                    + "                 responseObj.email = document.getElementById(\"email\").value;\n"
                    + "                 responseObj.phone = document.getElementById(\"phone\").value;\n"
                    + "                 responseArg = JSON.stringify(responseObj);\n"
                    + "                 console.log('responseArg: ' + responseArg);\n\n"

                    + "                 fetch('http://localhost:8083/booking', {\n"
                    + "                     method: 'POST',\n"
                    + "                     headers: { 'Content-Type': 'application/json' },\n"
                    + "                     body: responseArg\n"
                    + "                 })\n"
                    + "                 .then(response => {\n"
                    + "                     if (response.ok) {\n"
                    + "                         // Run the function for successful response here\n"
                    + "                         handleSuccess(name);\n"
                    + "                     } else {\n"
                    + "                         throw new Error('Request failed');\n"
                    + "                     }\n"
                    + "                 })\n"
                    + "                 .then(data => document.getElementById('response').innerText = JSON.stringify(data))\n"
                    + "                 .catch(error => console.error('Error:', error));\n"
                    + "             }\n\n"

                    + "             function handleSuccess(name) {\n"
                    + "                 console.log(\"HandleSuccess status ok!\");"

                    + "                 var form = document.getElementById(\"paymentForm\");\n"
                    + "                 form.style.display = \"none\";\n"

                    + "                 var responseElement = document.getElementById(\"showConfirmation\");\n"
                    + "                 responseElement.innerHTML = '<h2>Payment Successful!</h2><br>' +"
                    + "                     '<p>Congratulations ' + name + ' on your booking!<br>' + "
                    + "                     '" + type + " room, with " + beds + " bed" + (beds > 1 ? "s" : "") + "<br>Fabulous " + view + "<br>' + "
                    + "                     '" + (balcony ? "Includes a balcony<br>' + " : "' + ")
                    + "                     '" + (accessibility ? "Accessibility Access<br>' + " : "' + ")
                    + "                     '<br>Dates: " + checkIn + " to " + checkOut + "<br>' + "
                    + "                     'Amount Paid: " + price + "<br>' + "
                    + "                     'Reference Number: " + rootElement(responseArg, "reference") + "' + "
                    + "                     '</p>';"
                    + "             }\n"
                    + "         </script>\n"
                    + "    </body>\n"
                    + "</html>";
        }
        catch (Exception e) {
            System.out.println("Error processing payment. Raw response:\n" + responseArg);
            e.printStackTrace();
            return "<html>\n"
                    + "    <body>\n"
                    + "        <h1>Error</h1>\n"
                    + "        <p>An error occurred while processing the payment.</p>\n"
                    + "    </body>\n"
                    + "</html>";
        }
    }

    private String rootElement(String arg, String key) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(arg);

            return jsonNode.get(key).asText();

        } catch (Exception e) {
            System.out.println("\n");
            System.out.println("Error processing quote. Raw response:\n" + arg);
            //e.printStackTrace();
        }

        return "";
    }

    private String roomInfoElement(String arg, String key) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(arg);

            return jsonNode.get("roomInfo").get(key).asText();

        } catch (Exception e) {
            System.out.println("\n");
            System.out.println("Error processing quote. Raw response:\n" + arg);
            //e.printStackTrace();
        }
        return "";
    }

    private String jsonConstruct(String type, int beds, double bedSize, boolean balcony, String view, boolean accessibility, String checkIn, String checkOut, double price, int roomId) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.createObjectNode();

        //((ObjectNode) jsonNode).put("booking_ref", 5);
        ((ObjectNode) jsonNode).put("ID", roomId);
        ((ObjectNode) jsonNode).put("name", "bob");
        ((ObjectNode) jsonNode).put("email", "bob@gmail");
        ((ObjectNode) jsonNode).put("phone", "123");
        ((ObjectNode) jsonNode).put("type", type);
        ((ObjectNode) jsonNode).put("beds", beds);
        ((ObjectNode) jsonNode).put("bedSize", bedSize);
        ((ObjectNode) jsonNode).put("balcony", balcony);
        ((ObjectNode) jsonNode).put("view", view);
        ((ObjectNode) jsonNode).put("accessibility", accessibility);
        ((ObjectNode) jsonNode).put("checkIn", checkIn);
        ((ObjectNode) jsonNode).put("checkOut", checkOut);
        ((ObjectNode) jsonNode).put("price", price);

        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("bookingObject: " + jsonNode);
        System.out.println("jsonString: " + jsonString);
        return jsonString;
    }
}