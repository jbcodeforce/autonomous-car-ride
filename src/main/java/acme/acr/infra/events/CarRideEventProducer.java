package acme.acr.infra.events;

import acme.acr.domain.CarRide;

public interface CarRideEventProducer {

    void sendCarRideCreatedEventFrom(CarRide carRide);

    void sendCarRideUpdateEventFrom(CarRide carRide);

    void sendCarRideEvent(String key, CarRideEvent carRideEvent);

}