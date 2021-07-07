package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BusClient {
    private static final String API = "https://api.gov.uk/";

    static void findFirstFiveBuses(String id) {
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

        List<Arrival> arrivals = client.target("https://api.tfl.gov.uk/")
                .path("StopPoint/{id}/arrivals")
                .resolveTemplate("id", id)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Arrival>>() {});

        arrivals.sort(Comparator.comparing(Arrival::getMinutesToArrival));

        if (arrivals.isEmpty()) {
            System.out.println("No inbound buses found for this stop point");
        }

        for (int i = 0; i < 5 && i < arrivals.size(); i++) {
            System.out.println(arrivals.get(i));
        }
    }

    public static void findTwoClosestStops(String postcode){
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

        Postcode postcodeForBusStops = client.target("https://api.postcodes.io/postcodes/")
                .path("{postcode}")
                .resolveTemplate("postcode", postcode)
                .request(MediaType.APPLICATION_JSON)
                .get().readEntity(PostcodeResult.class).result;

        int numberOfStops = 0;
        int radius = 0;
        int[] possibleRadii = {100,200,500,1000,2000,5000,10000,20000,50000};
        List<BusStop> listOfBusStops = new ArrayList<>();
        while(numberOfStops < 2 && radius < 9) {
            listOfBusStops = getBusStopsInRadius(postcodeForBusStops, possibleRadii[radius]);
            radius++;
            numberOfStops = listOfBusStops.size();
        }
        if(numberOfStops>1){
            System.out.println("Arrivals at bus stop " + listOfBusStops.get(0).getCommonName());
            findFirstFiveBuses(listOfBusStops.get(0).getId());
            System.out.println("\nArrivals at bus stop " + listOfBusStops.get(1).getCommonName());
            findFirstFiveBuses(listOfBusStops.get(1).getId());
        } else if(numberOfStops == 1){
            System.out.println("Only one bus stop found in a radius of 50km.");
            System.out.println("Arrivals at bus stop " + listOfBusStops.get(0).getCommonName());
            findFirstFiveBuses(listOfBusStops.get(0).getId());
        } else {
            System.out.println("No bus stops found in a radius of 50km.");
        }
    }

    private static List<BusStop> getBusStopsInRadius(Postcode postcodeForBusStops, int radius) {
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        // String stopTypes = "NaptanPublicBusCoachTram";
        String stopTypes = "CarPickupSetDownArea%2C%20NaptanAirAccessArea%2C%20NaptanAirEntrance%2C%20NaptanAirportBuilding%2C%20NaptanBusCoachStation%2C%20NaptanBusWayPoint%2C%20NaptanCoachAccessArea%2C%20NaptanCoachBay%2C%20NaptanCoachEntrance%2C%20NaptanCoachServiceCoverage%2C%20NaptanCoachVariableBay%2C%20NaptanFerryAccessArea%2C%20NaptanFerryBerth%2C%20NaptanFerryEntrance%2C%20NaptanFerryPort%2C%20NaptanFlexibleZone%2C%20NaptanHailAndRideSection%2C%20NaptanLiftCableCarAccessArea%2C%20NaptanLiftCableCarEntrance%2C%20NaptanLiftCableCarStop%2C%20NaptanLiftCableCarStopArea%2C%20NaptanMarkedPoint%2C%20NaptanMetroAccessArea%2C%20NaptanMetroEntrance%2C%20NaptanMetroPlatform%2C%20NaptanMetroStation%2C%20NaptanOnstreetBusCoachStopCluster%2C%20NaptanOnstreetBusCoachStopPair%2C%20NaptanPrivateBusCoachTram%2C%20NaptanPublicBusCoachTram%2C%20NaptanRailAccessArea%2C%20NaptanRailEntrance%2C%20NaptanRailPlatform%2C%20NaptanRailStation%2C%20NaptanSharedTaxi%2C%20NaptanTaxiRank%2C%20NaptanUnmarkedPoint%2C%20TransportInterchange";
        stopTypes = Arrays.stream(stopTypes.split("%2C%20"))
                .filter(type -> type.toUpperCase().contains("BUS"))
                .collect(Collectors.joining(", "));
        return client.target("https://api.tfl.gov.uk/StopPoint")
                .queryParam("lat", postcodeForBusStops.getLatitude())
                .queryParam("lon", postcodeForBusStops.getLongitude())
                .queryParam("stopTypes", stopTypes)
                .queryParam("radius", radius)
                .queryParam("modes", "bus")
                .request(MediaType.APPLICATION_JSON)
                .get(BusStopResponse.class).getStopPoints();
    }

    private static List<String> getFirstFiveBuses(String id) {
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        List<String> output = new ArrayList<>();

        List<Arrival> arrivals = client.target("https://api.tfl.gov.uk/")
                .path("StopPoint/{id}/arrivals")
                .resolveTemplate("id", id)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Arrival>>() {});

        arrivals.sort(Comparator.comparing(Arrival::getMinutesToArrival));

        if (arrivals.isEmpty()) {
            output.add("No inbound buses found for this stop point\n");
        }

        for (int i = 0; i < 5 && i < arrivals.size(); i++) {
            output.add(arrivals.get(i) + "\n");
        }

        return output;
    }

    public static List<String> getTwoClosestStops(String postcode) {
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        List<String> output = new ArrayList<>();

        Postcode postcodeForBusStops = client.target("https://api.postcodes.io/postcodes/")
                .path("{postcode}")
                .resolveTemplate("postcode", postcode)
                .request(MediaType.APPLICATION_JSON)
                .get().readEntity(PostcodeResult.class).result;

        int numberOfStops = 0;
        int radius = 0;
        int[] possibleRadii = {100,200,500,1000,2000,5000,10000,20000,50000};
        List<BusStop> listOfBusStops = new ArrayList<>();
        while(numberOfStops < 2 && radius < 9) {
            listOfBusStops = getBusStopsInRadius(postcodeForBusStops, possibleRadii[radius]);
            radius++;
            numberOfStops = listOfBusStops.size();
        }

        if(numberOfStops>1){
            output.add("Arrivals at bus stop " + listOfBusStops.get(0).getCommonName() + "\n");
            output.addAll(getFirstFiveBuses(listOfBusStops.get(0).getId()));
            output.add("\nArrivals at bus stop " + listOfBusStops.get(1).getCommonName() + "\n");
            output.addAll(getFirstFiveBuses(listOfBusStops.get(1).getId()));
        } else if(numberOfStops == 1){
            output.add("Only one bus stop found in a radius of 50km.");
            output.add("\nArrivals at bus stop " + listOfBusStops.get(0).getCommonName() + "\n");
            output.addAll(getFirstFiveBuses(listOfBusStops.get(0).getId()));
        } else {
            output.add("No bus stops found in a radius of 50km.");
        }

        return output;
    }
}
