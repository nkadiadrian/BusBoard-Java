package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String args[]) throws IOException {
        String id = inputBusStopIDLoop();
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

        List<Arrival> arrivals = client.target("https://api.tfl.gov.uk/")
                .path("StopPoint/{id}/arrivals")
                .resolveTemplate("id", id)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Arrival>>() {});

        arrivals = arrivals.stream()
                .sorted(Comparator.comparing(Arrival::getMinutesToArrival))
                .collect(Collectors.toList());

        for (int i = 0; i < 5; i++) {
            System.out.println(arrivals.get(i));
        }


//        String arrivals = client.target("https://api.tfl.gov.uk/StopPoint/490008660N/arrivals")
//                .request(MediaType.APPLICATION_JSON)
//                .get(String.class);

//        ObjectMapper objectMapper = new ObjectMapper();

//        WebTarget myResource = client.target("https://api.tfl.gov.uk/StopPoint/490008660N/arrivals");
//        String response = myResource.request(MediaType.TEXT_PLAIN)
//                .get(String.class);

//        List<Arrival> arrivalsList = objectMapper.readValue(arrivals, new TypeReference<List<Arrival>>(){});
    }

    private static String inputBusStopIDLoop() {
        String id;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input Bus Stop ID: ");
        id = scanner.nextLine();

        return id;
    }
}
