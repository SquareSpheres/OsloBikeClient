package io.github.squarespheres.oslobikes.jsonmodels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ToString
public class StationAvailabilityManager {

    @Getter
    @JsonProperty("updated_at")
    private String lastUpdated;
    @Getter
    @JsonProperty("refresh_rate")
    private int refreshRate;

    private Map<Integer, StationAvailability> availabilityMap;


    @JsonCreator
    public StationAvailabilityManager(@JsonProperty("stations") List<StationAvailability> availabilityList) {
        availabilityMap = availabilityList.stream().collect(Collectors.toMap(StationAvailability::getId, item -> item));
    }

    public Map<Integer, StationAvailability> getStationAvailabilityMap() {
        return availabilityMap;
    }

    public List<StationAvailability> getStationAvailabilityList() {
        return new ArrayList<>(availabilityMap.values());
    }
}
