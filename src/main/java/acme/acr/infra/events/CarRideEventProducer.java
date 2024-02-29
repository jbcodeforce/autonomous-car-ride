package acme.acr.infra.events;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;

import io.smallrye.reactive.messaging.kafka.api.OutgoingKafkaRecordMetadata;

import acme.acr.domain.CarRide;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CarRideEventProducer {
    
    Logger logger = Logger.getLogger(CarRideEventProducer.class.getName());
    
    @Channel("car_rides")
	public Emitter<CarRideEvent> eventProducer;


    public void sendCarRideCreatedEventFrom(CarRide carRide) {
        CarRideEvent carRideEvent = new CarRideEvent("RideID_"+carRide.rideId);
        CarRideCreatedEvent oce = new CarRideCreatedEvent(carRide);
		carRideEvent.payload = oce;
        sendCarRideEvent(carRideEvent.eventId,carRideEvent);
    }

    public void sendCarRideUpdateEventFrom(CarRide carRide) {
        CarRideEvent carRideEvent = new CarRideEvent("RideID_"+carRide.rideId);
        CarRideUpdatedEvent oce = new CarRideUpdatedEvent(carRide);
		carRideEvent.payload = oce;
        sendCarRideEvent(carRideEvent.eventId,carRideEvent);
    }
     

    public void sendCarRideEvent(String key, CarRideEvent carRideEvent){
        logger.info("key " + key + " car ride event " + carRideEvent.eventId
                    + " type:" + carRideEvent.eventType 
                    + " ts: " + carRideEvent.timestampMillis);

		eventProducer.send(Message.of(carRideEvent).addMetadata(OutgoingKafkaRecordMetadata.<String>builder()
			.withKey(key).build())
			.withAck( () -> {
				
				return CompletableFuture.completedFuture(null);
			})
			.withNack( throwable -> {
				return CompletableFuture.completedFuture(null);
			}));
	}
}
