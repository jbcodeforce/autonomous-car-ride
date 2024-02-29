package acme.acr.domain;

import java.time.Instant;
import java.util.Objects;

import acme.acr.infra.DataGenerator;
import acme.acr.infra.GeoLocationUtils;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;


/**
 * CarRide represents an autonomous car ride done by a customer from starting point to destination 
 */
@Entity
public class CarRide extends PanacheEntityBase {
    public static final String TRIP_STATUS_ORDERED = "ORDERED";
    public static final String TRIP_STATUS_COMPLETED = "COMPLETED";
    public static final String TRIP_STATUS_IN_PROGRESS = "IN_PROGRESS";
    public static final String TRIP_STATUS_CANCELLED = "CANCELLED";
    public static final String TRIP_STATUS_UNKNOWN = "UNKNOWN";


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trip_seq")
    @SequenceGenerator(name = "trip_seq", sequenceName = "trip_sequence")
    public long rideId;
    public boolean isAStart;
    @Column(nullable = true)
    public Instant tripStartTime;
    @Column(nullable = true)
    public Instant tripEndTime;
    public String tripCompletionDate;
    public String customerID;
    public float startLon = 0;
    public float startLat = 0;
    public float endLon = 0;
    public float endLat = 0 ;
    public short passengerCnt = 0;
    public long carId = 1;
    public String tripStatus;
    
    public CarRide() {
        this.tripStartTime = Instant.now();
    }

    public CarRide(long rideId, boolean isAStart) {
        DataGenerator g = new DataGenerator(rideId);
        if (rideId != -1)
            this.rideId = rideId;
        this.isAStart = isAStart;
        this.tripStartTime = isAStart ? g.startTime() : g.endTime();
        this.startLon = g.startLon();
        this.startLat = g.startLat();
        this.endLon = g.endLon();
        this.endLat = g.endLat();
        this.passengerCnt = g.passengerCnt();
        this.carId = g.carId();
    }

    @Override
    public String toString() {

        return rideId
                + ","
                + (isAStart ? "START" : "END")
                + ","
                + tripStartTime.toString()
                + ","
                + startLon
                + ","
                + startLat
                + ","
                + endLon
                + ","
                + endLat
                + ","
                + passengerCnt
                + ","
                + carId;
    }

     /**
     * Compares this CarRide with the given one.
     *
     * <ul>
     *   <li>sort by timestamp,
     *   <li>putting START events before END events if they have the same timestamp
     * </ul>
     */
    public int compareTo(@Nullable CarRide other) {
        if (other == null) {
            return 1;
        }
        int compareTimes = this.tripStartTime.compareTo(other.tripStartTime);
        if (compareTimes == 0) {
            if (this.isAStart == other.isAStart) {
                return 0;
            } else {
                if (this.isAStart) {
                    return -1;
                } else {
                    return 1;
                }
            }
        } else {
            return compareTimes;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarRide carRide = (CarRide) o;
        return rideId == carRide.rideId
                && isAStart == carRide.isAStart
                && Float.compare(carRide.startLon, startLon) == 0
                && Float.compare(carRide.startLat, startLat) == 0
                && Float.compare(carRide.endLon, endLon) == 0
                && Float.compare(carRide.endLat, endLat) == 0
                && passengerCnt == carRide.passengerCnt
                && carId == carRide.carId
                && Objects.equals(tripStartTime, carRide.tripStartTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                rideId,
                isAStart,
                tripStartTime,
                startLon,
                startLat,
                endLon,
                endLat,
                passengerCnt,
                carId);
    }

    public double getEuclideanDistance(double longitude, double latitude) {
        if (this.isAStart) {
            return GeoLocationUtils.getEuclideanDistance(
                    (float) longitude, (float) latitude, this.startLon, this.startLat);
        } else {
            return GeoLocationUtils.getEuclideanDistance(
                    (float) longitude, (float) latitude, this.endLon, this.endLat);
        }
    }

    public long getEventTimeMillis() {
        return tripStartTime.toEpochMilli();
    }
}