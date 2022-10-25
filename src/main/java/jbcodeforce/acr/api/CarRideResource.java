package jbcodeforce.acr.api;

import java.net.URI;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jbcodeforce.acr.domain.CarRide;
import jbcodeforce.acr.infra.CarRideRepository;

@Path("/api/v1/carrides")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class CarRideResource {
    @Inject
    CarRideRepository repository;

    @GET
    @Path("/{id}")
    public CarRide get(Long id) {
        return repository.findById(id);
    }

    @GET
    public List<CarRide> getAllCarRides() {
        return repository.getAll();
    }

    @POST
    public Response createNewRide(CarRide aNewRide) {
        aNewRide = repository.createNewRide(aNewRide);
        return Response.ok(aNewRide).build();
    }
}
