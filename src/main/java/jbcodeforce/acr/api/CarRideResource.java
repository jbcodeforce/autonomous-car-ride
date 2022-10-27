package jbcodeforce.acr.api;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jbcodeforce.acr.api.dto.Control;
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

    /**
     * This is for demonstration purpose.
     * @return
     */
    @POST
    @Path("/generate")
    public Response startGenerating(Control ctl) {
        if ("start".equals(ctl.command)) {
            for (int i=1; i <= ctl.numberOfRecords; i++) {
                CarRide cr = new CarRide(-1,true);
                repository.createNewRide(cr);
            }
        }
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public CarRide update(Long id, CarRide carRide) {
        CarRide entity = repository.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.rideId = carRide.rideId;
        entity.endLat = carRide.endLat;
        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        CarRide entity = repository.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        repository.delete(entity);
    }

    @GET
    @Path("/count")
    public Long count() {
        return repository.count();
    }
}
