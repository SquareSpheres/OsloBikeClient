package io.github.squarespheres.oslobikes.jsonmodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@ToString
public class Bound {

    @Getter
    @JsonProperty("latitude")
    private double latitude;
    @Getter
    @JsonProperty("longitude")
    private double longitude;

}
