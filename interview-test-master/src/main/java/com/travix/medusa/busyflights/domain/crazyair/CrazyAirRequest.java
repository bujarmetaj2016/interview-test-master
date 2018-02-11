package com.travix.medusa.busyflights.domain.crazyair;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CrazyAirRequest {
    @JsonProperty
    private String origin;
    @JsonProperty
    private String destination;
    @JsonProperty
    private String departureDate;
    @JsonProperty
    private String returnDate;
    @JsonProperty
    private int passengerCount;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(final String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(final String destination) {
        this.destination = destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(final String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(final String returnDate) {
        this.returnDate = returnDate;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(final int passengerCount) {
        this.passengerCount = passengerCount;
    }
}
