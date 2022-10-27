package jbcodeforce.acr.domain;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.vertx.codegen.annotations.Nullable;
import jbcodeforce.acr.infra.DataGenerator;
import jbcodeforce.acr.infra.GeoLocationUtils;

/**
 * CarRide represents an autonomous car ride done by a customer from starting point to destination 
 */
@Entity
public class CarRide {
    @Id @GeneratedValue public long rideId;
    public boolean isAStart;
    @Column(nullable = true)
    public Instant eventTime;
    public float startLon = 0;
    public float startLat = 0;
    public float endLon = 0;
    public float endLat = 0 ;
    public short passengerCnt = 0;
    public long carId = 1;
    
    public CarRide() {
        this.eventTime = Instant.now();
    }

    public CarRide(long rideId, boolean isAStart) {
        DataGenerator g = new DataGenerator(rideId);
        if (rideId != -1)
            this.rideId = rideId;
        this.isAStart = isAStart;
        this.eventTime = isAStart ? g.startTime() : g.endTime();
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
                + eventTime.toString()
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
        int compareTimes = this.eventTime.compareTo(other.eventTime);
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
                && Objects.equals(eventTime, carRide.eventTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                rideId,
                isAStart,
                eventTime,
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
        return eventTime.toEpochMilli();
    }
}