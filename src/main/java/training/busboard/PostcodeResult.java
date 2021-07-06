package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostcodeResult {
    public Postcode result;

    public Postcode getResult() {
        return result;
    }
}
