package io.github.squarespheres.oslobikes.jsonmodels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@ToString
public class StationAvailability extends Station {

    @Getter
    private int locks;
    @Getter
    private int bikes;
    @Getter
    private boolean overflowCapacity;

    @JsonCreator
    public StationAvailability(@JsonProperty("availability") AvailableLocksAndBikes locksAndBikes) {
        this.locks = locksAndBikes.locks;
        this.bikes = locksAndBikes.bikes;
        this.overflowCapacity = locksAndBikes.overflowCapacity;

    }

    private static class AvailableLocksAndBikes {
        @JsonProperty("bikes")
        private int bikes;
        @JsonProperty("locks")
        private int locks;
        @JsonProperty("overflow_capacity")
        private boolean overflowCapacity;
    }
}
