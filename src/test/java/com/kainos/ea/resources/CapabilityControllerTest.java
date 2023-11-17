package com.kainos.ea.resources;

import org.junit.jupiter.api.Test;
import org.kainos.ea.api.CapabilityService;
import org.kainos.ea.cli.Capability;
import org.kainos.ea.client.ActionFailedException;
import org.kainos.ea.client.DoesNotExistException;
import org.kainos.ea.resources.CapabilityController;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CapabilityControllerTest {
    CapabilityService capabilityService = Mockito.mock(CapabilityService.class);
    CapabilityController capabilityController = new CapabilityController(capabilityService);

    @Test
    void capabilityController_shouldReturn500Response_whenCapabilityServiceThrowsActionFailedException() throws ActionFailedException {
        int expectedStatusCode = 500;

        Mockito.doThrow(ActionFailedException.class).when(capabilityService).getAllCapabilities();

        Response response = capabilityController.getAllCapabilities();

        assertEquals(response.getStatus(), expectedStatusCode);
    }

    @Test
    void getAllCapabilities_shouldReturn200Response_whenCapabilityServiceDoesNotThrowException() throws ActionFailedException {
        int expectedStatusCode = 200;

        Capability capability1 = new Capability(1,"Innovation");
        Capability capability2 = new Capability(2,"Engineering");
        Capability capability3 = new Capability(3,"Data & AI");

        List<Capability> capabilities = new ArrayList<>();
        capabilities.add(capability1);
        capabilities.add(capability2);
        capabilities.add(capability3);

        Mockito.when(capabilityService.getAllCapabilities()).thenReturn(capabilities);

        Response response = capabilityController.getAllCapabilities();

        assertEquals(response.getStatus(), expectedStatusCode);
        assertEquals(response.getEntity(), capabilities);
    }

    @Test
    void getControllerById_shouldReturn200Response_whenBandServiceDoesNotThrowException() throws ActionFailedException, DoesNotExistException {
        int id = 1;
        int expectedStatusCode = 200;

        Capability capability = new Capability(1,"Innovation");

        Mockito.when(capabilityService.getCapabilityById(id)).thenReturn(capability);

        Response response = capabilityController.getCapabilityById(id);

        assertEquals(response.getStatus(), expectedStatusCode);
        assertEquals(response.getEntity(), capability);
    }

    @Test
    void getControllerById_shouldReturn404Response_whenBandServiceThrowsDoesNotExistException() throws ActionFailedException, DoesNotExistException {
        int id = 1;
        int expectedStatusCode = 404;

        Mockito.doThrow(DoesNotExistException.class).when(capabilityService).getCapabilityById(id);

        Response response = capabilityController.getCapabilityById(id);

        assertEquals(response.getStatus(), expectedStatusCode);
    }

    @Test
    void getControllerById_shouldReturn500Response_whenBandServiceThrowsDoesNotExistException() throws ActionFailedException, DoesNotExistException {
        int id = 1;
        int expectedStatusCode = 500;

        Mockito.doThrow(ActionFailedException.class).when(capabilityService).getCapabilityById(id);

        Response response = capabilityController.getCapabilityById(id);

        assertEquals(response.getStatus(), expectedStatusCode);
    }
}
