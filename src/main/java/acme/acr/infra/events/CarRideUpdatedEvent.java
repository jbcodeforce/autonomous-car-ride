package acme.acr.infra.events;

import java.time.Instant;

import acme.acr.domain.CarRide;

public class CarRideUpdatedEvent extends CarRideVariablePayload {

    public long carId;
    public long rideId;
    public String tripStatus;
    public Instant tripStartTime;
    public Instant tripEndTime;
    public String tripCompletionDate;
    public String customerID;
    public float startLon = 0;
    public float startLat = 0;
    public float endLon = 0;
    public float endLat = 0 ;
    public short passengerCnt = 0;

    public CarRideUpdatedEvent(CarRide carRide) {
        this.carId=carRide.carId;
        this.rideId=carRide.rideId;
        this.tripStartTime=carRide.tripStartTime;
        this.tripEndTime=carRide.tripEndTime;
        this.startLon=carRide.startLon;
        this.startLat=carRide.startLat;
        this.endLon=carRide.endLon;
        this.endLat=carRide.endLat;
        this.passengerCnt=carRide.passengerCnt;
        this.customerID=carRide.customerID;
        this.tripStatus=carRide.tripStatus;
        this.tripCompletionDate=carRide.tripCompletionDate;
    }

}
