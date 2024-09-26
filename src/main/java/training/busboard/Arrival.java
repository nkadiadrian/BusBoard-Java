package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Arrival {
    private int minutesToArrival;
    private Date expectedArrival;
    private int lineId;
    private String destinationName;

    public Arrival() {
    }

    public Arrival(Date expectedArrival, int lineId, String destinationName) {
        this.expectedArrival = expectedArrival;
        this.lineId = lineId;
        this.destinationName = destinationName;
    }

    public void setExpectedArrival(Date expectedArrival) {
        this.expectedArrival = expectedArrival;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public int getMinutesToArrival() {
        Date currentDate = new Date();
        minutesToArrival = Math.round((expectedArrival.getTime() - currentDate.getTime())/60000);
        return minutesToArrival;
    }

    @Override
    public String toString() {
        return "Expected Arrival in " + getMinutesToArrival() +
                " mins: \t" + lineId +
                " going to \t" + destinationName;
    }
}
