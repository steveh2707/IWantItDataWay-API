package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;
import org.kainos.ea.api.CapabilityService;
import org.kainos.ea.client.ActionFailedException;
import org.kainos.ea.client.DoesNotExistException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("I Want It Data Way API")
@Path("/api")
public class CapabilityController {

    private final CapabilityService capabilityService;

    public CapabilityController(CapabilityService capabilityService) {
        this.capabilityService = capabilityService;
    }

    @GET
    @Path("/capabilities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCapabilities() {
        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(capabilityService.getAllCapabilities())
                    .build();
        } catch (ActionFailedException e) {
            System.out.println(e.getMessage());
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }

    @GET
    @Path("/capabilities/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCapabilityById(@PathParam("id") int id){
        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(capabilityService.getCapabilityById(id))
                    .build();
        } catch (ActionFailedException e) {
            System.out.println(e.getMessage());
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        } catch (DoesNotExistException e) {
            System.out.println(e.getMessage());
            return Response.status(HttpStatus.NOT_FOUND_404).build();
        }
    }
}
