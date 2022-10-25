package jbcodeforce.acr.infra;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jbcodeforce.acr.domain.CarRide;


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
