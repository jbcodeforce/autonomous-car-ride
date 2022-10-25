package jbcodeforce.acr.ut;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jbcodeforce.acr.domain.CarRide;

//@QuarkusTest
public class TestCarRide {
    
    @Test
    public void createCarRide() throws JsonProcessingException{
        CarRide carRide1 = new CarRide(1,true);
        System.out.println(carRide1.toString());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); 
        System.out.println(mapper.writeValueAsString(carRide1));
    }

}
