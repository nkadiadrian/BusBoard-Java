package training.busboard;

import com.fasterxml.jackson.core.type.TypeReference;
import org.glassfish.jersey.jackson.JacksonFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String args[]) throws IOException {
//        Client client = ClientBuilder.newBuilder().build();

        // String jsonData = client.target("https://api.tfl.gov.uk/StopPoint/490008660N/arrivals").request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<String>() {});
//
//        String arrivals = client.target("https://api.tfl.gov.uk/StopPoint/490008660N/arrivals")
//                .request(MediaType.TEXT_PLAIN)
//                .get(String.class);
//
//        ObjectMapper objectMapper = new ObjectMapper();

        Client client = ClientBuilder.newClient();
        WebTarget myResource = client.target("https://api.tfl.gov.uk/StopPoint/490008660N/arrivals");
        String response = myResource.request(MediaType.TEXT_PLAIN)
                .get(String.class);

        System.out.println(response);



        //List<Arrival> arrivals = objectMapper.readValue(jsonData, new TypeReference<List<Arrival>>(){});
        // System.out.println(arrivals.get(0));
    }
}	
