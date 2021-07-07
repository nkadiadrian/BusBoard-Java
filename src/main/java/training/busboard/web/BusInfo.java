package training.busboard.web;

import training.busboard.BusClient;

import java.util.ArrayList;
import java.util.List;

public class BusInfo {
    private final String postcode;
    private List<String> arrivalsAtTwoClosestStops = new ArrayList<>();

    public BusInfo(String postcode) {
        this.postcode = postcode;
        this.arrivalsAtTwoClosestStops = BusClient.getTwoClosestStops(postcode);
    }

    public List<String> getArrivalsAtTwoClosestStops() {
        return arrivalsAtTwoClosestStops;
    }

    public String getPostcode() {
        return postcode;
    }
}
