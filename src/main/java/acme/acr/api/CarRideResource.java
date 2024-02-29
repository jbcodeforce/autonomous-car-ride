package acme.acr.api;

import java.util.List;

import acme.acr.api.dto.Control;
import acme.acr.domain.CarRide;
import acme.acr.infra.CarRideRepository;
import acme.acr.infra.events.CarRideEventProducer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/carrides")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class CarRideResource {

    @Inject
    CarRideRepository repository;

    @Inject
    CarRideEventProducer eventProducer;

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
        aNewRide.tripStatus=CarRide.TRIP_STATUS_ORDERED;
        aNewRide = repository.createNewRide(aNewRide);
        eventProducer.sendCarRideCreatedEventFrom(aNewRide);
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
