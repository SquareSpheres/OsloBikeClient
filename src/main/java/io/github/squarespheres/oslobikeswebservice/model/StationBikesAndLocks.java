package io.github.squarespheres.oslobikeswebservice.model;

public class StationBikesAndLocks {

    private final String stationName;
    private final int numberOfBikes;
    private final int numberOfLocks;

    public StationBikesAndLocks(String stationName, int numberOfBikes, int numberOfLocks) {
        this.stationName = stationName;
        this.numberOfBikes = numberOfBikes;
        this.numberOfLocks = numberOfLocks;
    }


    public String getStationName() {
        return stationName;
    }

    public int getNumberOfBikes() {
        return numberOfBikes;
    }

    public int getNumberOfLocks() {
        return numberOfLocks;
    }
}
