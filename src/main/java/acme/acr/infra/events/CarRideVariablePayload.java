package acme.acr.infra.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use=Id.NAME,
        include = As.PROPERTY,
        property = "@type")
@JsonSubTypes({
    @Type(value=CarRideCreatedEvent.class, name="CarRideCreatedEvent"),
    @Type(value=CarRideUpdatedEvent.class, name="CarRideUpdatedEvent")})
public abstract class CarRideVariablePayload {

}
