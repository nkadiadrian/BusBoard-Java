package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusStopResponse {
    public List<BusStop> getStopPoints() {
        return stopPoints;
    }

    private List<BusStop> stopPoints;
}
