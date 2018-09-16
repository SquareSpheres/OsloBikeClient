package io.github.squarespheres.oslobikeswebservice.webservice;

import io.github.squarespheres.oslobikes.client.OsloBikeClient;
import io.github.squarespheres.oslobikes.jsonmodels.StationAvailability;
import io.github.squarespheres.oslobikes.jsonmodels.StationInfo;
import io.github.squarespheres.oslobikeswebservice.model.StationBikesAndLocks;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class StationAvailabilityController {

    private static Logger logger = LoggerFactory.getLogger(StationAvailabilityController.class);

    @Value("${osloApiId}")
    private String osloBikeclientId;

    private final OsloBikeClient osloBikeClient = new OsloBikeClient();

    private boolean verifyClientId(String clientId) {
        // Client verification code here
        return true;
    }

    @RequestMapping(value = "/availability", method = RequestMethod.GET)
    public List<StationBikesAndLocks> availableBikesAndLocks(
            @RequestHeader("Client-Identifier") String clientId,
            @RequestParam(value = "stationId", required = false) Integer stationId,
            HttpServletRequest servletRequest)
            throws IOException, InvalidClientIdException {

        if (!verifyClientId(clientId)) {
            // log failed verification attempts
            logger.warn(servletRequest.getRemoteAddr() + " : Invalid client-id");
            throw new InvalidClientIdException("Invalid client-id");
        }

        Map<Integer, StationAvailability> availabilityMap = osloBikeClient.getAllStationAvailabilityMap(osloBikeclientId);
        Map<Integer, StationInfo> infoMap = osloBikeClient.getAllStationInformationMap(osloBikeclientId);

        List<StationBikesAndLocks> availableBikesAndLocks = new ArrayList<>();

        if (stationId == null) {
            for (StationInfo stationInfo : infoMap.values()) {
                StationAvailability availability = availabilityMap.get(stationInfo.getId());
                availableBikesAndLocks.add(new StationBikesAndLocks(stationInfo.getTitle(), availability.getBikes(), availability.getLocks()));
            }
        } else {
            StationInfo stationInfo = infoMap.get(stationId);
            StationAvailability stationAvailability = availabilityMap.get(stationId);
            availableBikesAndLocks.add(new StationBikesAndLocks(stationInfo.getTitle(), stationAvailability.getBikes(), stationAvailability.getLocks()));
        }

        return availableBikesAndLocks;
    }
}
