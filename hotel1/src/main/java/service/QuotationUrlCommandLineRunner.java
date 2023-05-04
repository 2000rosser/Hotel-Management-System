package service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import java.io.InputStream;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.entity.ContentType;


@Component
public class QuotationUrlCommandLineRunner implements CommandLineRunner {
    
    private String url = "http://hotel1:8080/quotations";


    @Override
    public void run(String... args) throws Exception {

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://hms:8083/applications/services");
        StringEntity entity = new StringEntity("http://hotel1:8080/quotations", ContentType.TEXT_PLAIN);
        httpPost.setEntity(entity);
        HttpResponse response = httpClient.execute(httpPost);

    }
}
