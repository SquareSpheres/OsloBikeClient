package io.github.squarespheres.oslobikes.client;

import io.github.squarespheres.oslobikes.jsonmodels.StationAvailability;
import io.github.squarespheres.oslobikes.jsonmodels.StationAvailabilityManager;
import io.github.squarespheres.oslobikes.jsonmodels.StationInfo;
import io.github.squarespheres.oslobikes.jsonmodels.StationInformationManager;
import io.github.squarespheres.oslobikes.utils.TimeUtils;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OsloBikeClient extends AbstractOsloBikeClient {

    private static final Logger log = Logger.getLogger(OsloBikeClient.class.getSimpleName());
    private StationAvailabilityManager availabilityManager;
    private StationInformationManager informationManager;

    /**
     * Create or update the {@link StationInformationManager} by connecting to the host and fetching most recent info
     *
     * @param clientId API client id
     * @throws IOException if connection or parsing failed
     */
    private void getAvailabilityManager(String clientId) throws IOException {
        this.availabilityManager = callHostAPI(clientId, "stations/availability", StationAvailabilityManager.class);
    }

    /**
     * Create or update the {@link StationAvailabilityManager} by connecting to the host and fetching most recent info
     *
     * @param clientId API client id
     * @throws IOException if connection or parsing failed
     */
    private void getInformationManager(String clientId) throws IOException {
        this.informationManager = callHostAPI(clientId, "stations", StationInformationManager.class);
    }

    /**
     * Get a map of all the station availability
     *
     * @param clientId API client id
     * @return map of all the stations and their availability.
     * @throws IOException if connection or parsing failed
     */
    public Map<Integer, StationAvailability> getAllStationAvailabilityMap(String clientId) throws IOException {

        if (this.availabilityManager == null) {
            getAvailabilityManager(clientId);
        }

        if (TimeUtils.TimeDiffInSeconds(TimeUtils.TimeNow(), TimeUtils.TimeFromString(availabilityManager.getLastUpdated())) > availabilityManager.getRefreshRate()) {
            log.log(Level.INFO, "Host api has refreshed, fetching new availability info");
            getAvailabilityManager(clientId);
            return this.availabilityManager.getStationAvailabilityMap();
        } else {
            return this.availabilityManager.getStationAvailabilityMap();
        }
    }

    /**
     * Get a map of all the station information
     *
     * @param clientId API client id
     * @throws IOException if connection or parsing failed
     */
    public Map<Integer, StationInfo> getAllStationInformationMap(String clientId) throws IOException {
        if (this.informationManager == null) {
            getInformationManager(clientId);
        }
        return this.informationManager.getStationInfoMap();
    }
}


