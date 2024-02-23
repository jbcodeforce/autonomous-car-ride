package jbcodeforce.acr.ut;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.Inject;

import acme.acr.domain.CarRide;
import acme.acr.infra.CarRideRepository;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class TestCarRide {

    @Inject
    CarRideRepository repository;
    
    @Test
    public void createCarRide() throws JsonProcessingException{
        CarRide carRide1 = new CarRide(1,true);
        carRide1.rideId = 0;
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); 
        System.out.println(mapper.writeValueAsString(carRide1));
        CarRide out = repository.createNewRide(carRide1);
        Assertions.assertNotNull(out.rideId);
    }

}
