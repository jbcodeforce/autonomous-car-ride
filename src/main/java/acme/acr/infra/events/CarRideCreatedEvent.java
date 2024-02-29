package acme.acr.infra.events;

import acme.acr.domain.CarRide;

public class CarRideCreatedEvent extends CarRideVariablePayload {

    public long carId;
    public long rideId;
    public String customerID;
    public String tripStatus;
    public String tripCompletionDate;

    public CarRideCreatedEvent(CarRide carRide) {
        this.carId=carRide.carId;
        this.rideId=carRide.rideId;
        this.customerID=carRide.customerID;
        this.tripStatus=carRide.tripStatus;
        this.tripCompletionDate=carRide.tripCompletionDate;
    }

}
