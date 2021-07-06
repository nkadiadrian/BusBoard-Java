package training.busboard.web;

import training.busboard.BusClient;

public class BusInfo {
    private final String postcode;
    private String arrivalsAtTwoClosestStops;

    public BusInfo(String postcode) {
        this.postcode = postcode;
        this.arrivalsAtTwoClosestStops = BusClient.getTwoClosestStops(postcode);
    }

    public String getArrivalsAtTwoClosestStops() {
        return arrivalsAtTwoClosestStops;
    }

    public String getPostcode() {
        return postcode;
    }
}
