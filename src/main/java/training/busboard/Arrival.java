package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Arrival {
    //private LocalDateTime expectedArrival;
    private int lineId;
    private String lineName;

    public Arrival() {
    }
//
//    public Arrival(LocalDateTime expectedArrival, int lineId, String lineName) {
//        this.expectedArrival = expectedArrival;
//        this.lineId = lineId;
//        this.lineName = lineName;
//    }
//
//    public void setExpectedArrival(LocalDateTime expectedArrival) {
//        this.expectedArrival = expectedArrival;
//    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    @Override
    public String toString() {
        return "Arrival{" +
                //"expectedArrival=" + expectedArrival +
                ", lineId=" + lineId +
                ", lineName='" + lineName + '\'' +
                '}';
    }
}
