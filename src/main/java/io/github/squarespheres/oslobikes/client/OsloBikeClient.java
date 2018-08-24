package io.github.squarespheres.oslobikes.client;

import io.github.squarespheres.oslobikes.jsonmodels.StationAvailability;
import io.github.squarespheres.oslobikes.jsonmodels.StationAvailabilityManager;
import io.github.squarespheres.oslobikes.jsonmodels.StationInfo;
import io.github.squarespheres.oslobikes.jsonmodels.StationInformationManager;
import io.github.squarespheres.oslobikes.utils.TimeUtils;

import java.io.IOException;
import java.util.Collections;
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
     * @return true if operation was successful
     */
    private boolean getAvailabilityManager(String clientId) {

        try {
            this.availabilityManager = callHostAPI(clientId, "stations/availability", StationAvailabilityManager.class);
        } catch (IOException e) {
            log.log(Level.WARNING, "Unable to fetch station availability from host", e);
            return false;
        }
        return true;
    }

    /**
     * Create or update the {@link StationAvailabilityManager} by connecting to the host and fetching most recent info
     *
     * @param clientId API client id
     * @return true if operation was successful
     */
    private boolean getInformationManager(String clientId) {
        try {
            this.informationManager = callHostAPI(clientId, "stations", StationInformationManager.class);
        } catch (IOException e) {
            log.log(Level.WARNING, "unable to fetch station information from host", e);
            return false;
        }
        return true;
    }

    /**
     * Get a map of all the station availability
     *
     * @param clientId API client id
     * @return map of all the stations and their availability, or empty map if connecting to host fails
     */
    public Map<Integer, StationAvailability> getAllStationAvailabilityMap(String clientId) {
        if (this.availabilityManager == null) {
            if (!getAvailabilityManager(clientId)) {
                return Collections.emptyMap();
            }
        }

        if (TimeUtils.TimeDiffInSeconds(TimeUtils.TimeNow(), TimeUtils.TimeFromString(availabilityManager.getLastUpdated())) > availabilityManager.getRefreshRate()) {
            log.log(Level.INFO, "Host api has refreshed, fetching new availability info");
            if (!getAvailabilityManager(clientId)) {
                return Collections.emptyMap();
            }
        }
        return this.availabilityManager.getStationAvailabilityMap();
    }

    /**
     * Get a map of all the station information
     *
     * @param clientId API client id
     * @return map of all the stations and their information, or empty map if connecting to the host fails
     */
    public Map<Integer, StationInfo> getAllStationInformationMap(String clientId) {
        if (this.informationManager == null) {
            if (!getInformationManager(clientId)) {
                return Collections.emptyMap();
            }
        }
        return this.informationManager.getStationInfoMap();
    }
}
