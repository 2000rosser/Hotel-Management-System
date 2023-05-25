package service.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaymentsController {
    @GetMapping("/payments")
    @ResponseBody
    public String handlePayments(@RequestParam("responseArg") String responseArg) {
        // Process the response argument and generate the appropriate HTML content

        try {
            // Return an HTML form with the JSON data embedded in it
            return "<html>\n"
                    + "    <body>\n"
                    + "        <h1>Payment Page</h1>\n"
                    + "        <form id=\"paymentForm\">\n"
                    + "            <input type=\"hidden\" name=\"responseArg\" id=\"responseArg\" value=\'" + responseArg.replace("'", "&#39;") + "\'/>\n"
                    + "            <input type=\"button\" value=\"Submit Payment\" onclick=\"submitPayment()\"/>\n"
                    + "        </form>\n"
                    + "        <div id=\"response\"></div>\n"
                    + "        <script>\n"
                    + "        function submitPayment() {\n"
                    + "            var responseArg = document.getElementById('responseArg').value;\n"
                    + "            fetch('http://localhost:8083/booking', {\n"
                    + "                method: 'POST',\n"
                    + "                headers: { 'Content-Type': 'application/json' },\n"
                    + "                body: responseArg\n"
                    + "            })\n"
                    + "            .then(response => response.json())\n"
                    + "            .then(data => document.getElementById('response').innerText = JSON.stringify(data))\n"
                    + "            .catch(error => console.error('Error:', error));\n"
                    + "        }\n"
                    + "        </script>\n"
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
}

