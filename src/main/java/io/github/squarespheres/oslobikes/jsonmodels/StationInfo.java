package io.github.squarespheres.oslobikes.jsonmodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
public class StationInfo extends Station {

    @Getter
    @JsonProperty("title")
    private String title;
    @Getter
    @JsonProperty("subtitle")
    private String subtitle;
    @Getter
    @JsonProperty("number_of_locks")
    private int numberOfLocks;
    @Getter
    @JsonProperty("center")
    private Bound centerBound;
    @Getter
    @JsonProperty("in_service")
    private boolean inService;
    @Getter
    @JsonProperty("bounds")
    private List<Bound> bounds;

}
