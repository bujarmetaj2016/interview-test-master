package com.travix.medusa.busyflights.domain.busyflights;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class BusyFlightsResponse {
    @JsonProperty
    private String airline;
    @JsonProperty
    private String supplier;
    @JsonProperty
    private Double total;
    @JsonProperty
    private String departureAirportCode;
    @JsonProperty
    private String destinationAirportCode;
    @JsonProperty
    private String departureDate;
    @JsonProperty
    private String arrivalDate;

    public String getAirline() {
        return airline;
    }

    public BusyFlightsResponse setAirline(String airline) {
        this.airline = airline;
        return this;
    }

    public String getSupplier() {
        return supplier;
    }

    public BusyFlightsResponse setSupplier(String supplier) {
        this.supplier = supplier;
        return this;
    }

    public Double getTotal() {
        return total;
    }

    public BusyFlightsResponse setTotal(Double total) {
        this.total = total;
        return this;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public BusyFlightsResponse setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
        return this;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    public BusyFlightsResponse setDestinationAirportCode(String destinationAirportCode) {
        this.destinationAirportCode = destinationAirportCode;
        return this;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public BusyFlightsResponse setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
        return this;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public BusyFlightsResponse setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusyFlightsResponse that = (BusyFlightsResponse) o;
        return Objects.equals(airline, that.airline) &&
                Objects.equals(supplier, that.supplier) &&
                Objects.equals(total, that.total) &&
                Objects.equals(departureAirportCode, that.departureAirportCode) &&
                Objects.equals(destinationAirportCode, that.destinationAirportCode) &&
                Objects.equals(departureDate, that.departureDate) &&
                Objects.equals(arrivalDate, that.arrivalDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(airline, supplier, total, departureAirportCode, destinationAirportCode, departureDate, arrivalDate);
    }

    @Override
    public String toString() {
        return "BusyFlightsResponse{" +
                "airline='" + airline + '\'' +
                ", supplier='" + supplier + '\'' +
                ", total=" + total +
                ", departureAirportCode='" + departureAirportCode + '\'' +
                ", destinationAirportCode='" + destinationAirportCode + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                '}';
    }
}
