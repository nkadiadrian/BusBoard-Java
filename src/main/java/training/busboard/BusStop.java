package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusStop {
    private String id;
    private String commonName;

    public String getId() {
        return id;
    }

    public String getCommonName() {
        return commonName;
    }

    public BusStop() {
    }

    @Override
    public String toString() {
        return "BusStop{" +
                "id='" + id + '\'' +
                ", commonName='" + commonName + '\'' +
                '}';
    }
}
