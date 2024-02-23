package acme.acr.infra;

import java.util.List;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import acme.acr.domain.CarRide;
import io.quarkus.hibernate.orm.panache.PanacheRepository;


@ApplicationScoped
public class CarRideRepository implements PanacheRepository<CarRide> {

    public List<CarRide> getAll(){
        return listAll();
    }

    @Transactional
    public CarRide createNewRide(CarRide aCarRide) {
        persist(aCarRide);
        return aCarRide;
    }
}
