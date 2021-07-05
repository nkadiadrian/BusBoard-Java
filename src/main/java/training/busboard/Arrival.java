package training.busboard;

import java.time.LocalDateTime;

public class Arrival {
    private LocalDateTime expectedArrival;
    private int lineId;
    private String lineName;

    public Arrival(LocalDateTime expectedArrival, int lineId, String lineName) {
        this.expectedArrival = expectedArrival;
        this.lineId = lineId;
        this.lineName = lineName;
    }

    public void setExpectedArrival(LocalDateTime expectedArrival) {
        this.expectedArrival = expectedArrival;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    @Override
    public String toString() {
        return "Arrivals{" +
                "expectedArrival=" + expectedArrival +
                ", lineId=" + lineId +
                ", lineName='" + lineName + '\'' +
                '}';
    }
}
