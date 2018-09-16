package io.github.squarespheres.oslobikes.client;

import io.github.squarespheres.oslobikes.jsonmodels.StationAvailability;
import io.github.squarespheres.oslobikes.jsonmodels.StationAvailabilityManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import static org.junit.Assume.*;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.*;

public class OsloBikeClientTest {

    private OsloBikeClient osloBikeClient;
    private static String clientId;

    @BeforeClass
    public static void setupClass() {
        clientId = System.getProperty("clientId");
    }

    @Before
    public void setUpTest() {
        osloBikeClient = new OsloBikeClient();
    }


    @Test(expected = IOException.class)
    public void wrongPathThrowsIoException() throws IOException {
        osloBikeClient.callHostAPI(clientId, "wong_path", StationAvailabilityManager.class);
    }

    @Test(expected = IOException.class)
    public void wrongClientIdThrowsIoException() throws IOException {
        osloBikeClient.callHostAPI("Wrong id", "stations", StationAvailabilityManager.class);
    }

    @Test(expected = IOException.class)
    public void unexpectedClassIdThrowsIoException() throws IOException {
        osloBikeClient.callHostAPI(clientId, "stations", Integer.class);
    }

    @Test
    public void getAllStationAvailabilityMapIsNotEmpty() {
        try {
            assertFalse(osloBikeClient.getAllStationAvailabilityMap(clientId).isEmpty());
        } catch (IOException e) {
            assumeNoException(e);
        }
    }

    @Test
    public void getAllStationInformationMapIsNotEmpty() {
        try {
            assertFalse(osloBikeClient.getAllStationInformationMap(clientId).isEmpty());
        } catch (IOException e) {
            assumeNoException(e);
        }
    }

    @Test
    public void getAllStationAvailabilityMapUseCachedVersion() {
        Map<Integer, StationAvailability> availabilityMap1 = null;
        Map<Integer, StationAvailability> availabilityMap2 = null;
        try {
            availabilityMap1 = osloBikeClient.getAllStationAvailabilityMap(clientId);
            availabilityMap2 = osloBikeClient.getAllStationAvailabilityMap(clientId);

        } catch (IOException e) {
            assumeNoException(e);
        }

        assertSame(availabilityMap1, availabilityMap2);
    }

//    I know not to use real time in Junit tests. Instead I could create a mock time object and forward time by a set amount.
//    @Test
//    public void getAllStationAvailabilityMapUseNewVersion() throws IOException, InterruptedException {
//        Map<Integer, StationAvailability> availabilityMap1 = osloBikeClient.getAllStationAvailabilityMap(clientId);
//        Thread.sleep(11000);
//        Map<Integer, StationAvailability> availabilityMap2 = osloBikeClient.getAllStationAvailabilityMap(clientId);
//        assertNotSame(availabilityMap1,availabilityMap2);
//    }


}