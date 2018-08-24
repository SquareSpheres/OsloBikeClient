package io.github.squarespheres.oslobikes;

import io.github.squarespheres.oslobikes.client.OsloBikeClient;
import io.github.squarespheres.oslobikes.jsonmodels.StationAvailability;
import io.github.squarespheres.oslobikes.jsonmodels.StationInfo;

import java.util.Map;

import static java.lang.System.exit;

public class ListAvailableBikesAndLocks {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Missing client ID");
            exit(-1);
        }

        OsloBikeClient client = new OsloBikeClient();
        Map<Integer, StationAvailability> availabilityMap = client.getAllStationAvailabilityMap(args[0]);
        Map<Integer, StationInfo> infoMap = client.getAllStationInformationMap(args[0]);

        // Print out stations name and availability
        for (StationInfo stationInfo : infoMap.values()) {
            StationAvailability availability = availabilityMap.get(stationInfo.getId());
            System.out.println("-------" + stationInfo.getTitle() + "-------");
            System.out.println("Number of locks : " + availability.getLocks());
            System.out.println("Number of bikes : " + availability.getBikes());
            System.out.println();
        }


    }

}
