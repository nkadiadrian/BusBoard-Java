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
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

        // String jsonData = client.target("https://api.tfl.gov.uk/StopPoint/490008660N/arrivals").request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<String>() {});

//        List<Arrival> arrivals = client.target("https://api.tfl.gov.uk/StopPoint/490008660N/arrivals")
//                .request(MediaType.APPLICATION_JSON)
//                .get(new GenericType<List<Arrival>>() {});

        String arrivals = client.target("https://api.tfl.gov.uk/StopPoint/490008660N/arrivals")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        ObjectMapper objectMapper = new ObjectMapper();
//
//        WebTarget myResource = client.target("https://api.tfl.gov.uk/StopPoint/490008660N/arrivals");
//        String response = myResource.request(MediaType.TEXT_PLAIN)
//                .get(String.class);

        List<Arrival> arrivalsList = objectMapper.readValue(arrivals, new TypeReference<List<Arrival>>(){});
        System.out.println(arrivals);
    }
}	
