package acme.acr.infra.events;

public class CarRideEvent {
    public static final String DEFAULT_VERSION = "1.0.0";
    public static final String CAR_RIDE_CREATED_TYPE = "CarRideCreated";
    public static final String CAR_RIDE_UPDATED_TYPE = "CarRideUpdated";

    public long timestampMillis;
    public String eventType;
    public String version;
    public String status;
    public CarRideVariablePayload  payload;
}
