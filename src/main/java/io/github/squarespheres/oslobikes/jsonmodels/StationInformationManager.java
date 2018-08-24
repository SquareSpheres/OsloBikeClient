package io.github.squarespheres.oslobikes.jsonmodels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ToString
public class StationInformationManager {

    private Map<Integer, StationInfo> stationInfoMap;

    @JsonCreator
    public StationInformationManager(@JsonProperty("stations") List<StationInfo> stations) {
        this.stationInfoMap = stations.stream().collect(Collectors.toMap(StationInfo::getId, item -> item));
    }

    public Map<Integer, StationInfo> getStationInfoMap() {
        return stationInfoMap;
    }

    public List<StationInfo> getStationInfoList() {
        return new ArrayList<>(stationInfoMap.values());
    }
}
