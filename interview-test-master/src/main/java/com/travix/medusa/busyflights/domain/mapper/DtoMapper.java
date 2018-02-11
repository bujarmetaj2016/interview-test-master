package com.travix.medusa.busyflights.domain.mapper;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.springframework.stereotype.Service;

@Service
public class DtoMapper {

    public CrazyAirRequest toCrazyAir(BusyFlightsRequest busyFlightsRequest) {
        final CrazyAirRequest crazyAirRequest = new CrazyAirRequest();
        crazyAirRequest.setDepartureDate(busyFlightsRequest.getDepartureDate());
        crazyAirRequest.setDestination(busyFlightsRequest.getDestination());
        crazyAirRequest.setOrigin(busyFlightsRequest.getOrigin());
        crazyAirRequest.setPassengerCount(busyFlightsRequest.getNumberOfPassengers());
        crazyAirRequest.setReturnDate(busyFlightsRequest.getReturnDate());
        return crazyAirRequest;
    }

    public BusyFlightsResponse fromCrazyAir(CrazyAirResponse crazyAirResponse) {
        return new BusyFlightsResponse()
                .setDepartureDate(crazyAirResponse.getDepartureDate())
                .setDestinationAirportCode(crazyAirResponse.getDestinationAirportCode())
                .setDepartureAirportCode(crazyAirResponse.getDepartureAirportCode())
                .setArrivalDate(crazyAirResponse.getArrivalDate())
                .setAirline(crazyAirResponse.getAirline())
                .setTotal(crazyAirResponse.getPrice());
    }

    public ToughJetRequest toToughJet(BusyFlightsRequest busyFlightsRequest) {
        final ToughJetRequest toughJetRequest = new ToughJetRequest();
        toughJetRequest.setOutboundDate(busyFlightsRequest.getDepartureDate());
        toughJetRequest.setTo(busyFlightsRequest.getDestination());
        toughJetRequest.setFrom(busyFlightsRequest.getOrigin());
        toughJetRequest.setNumberOfAdults(busyFlightsRequest.getNumberOfPassengers());
        toughJetRequest.setInboundDate(busyFlightsRequest.getReturnDate());
        return toughJetRequest;
    }

    public BusyFlightsResponse fromToughJet(ToughJetResponse toughJetResponse) {
        final double basePrice = toughJetResponse.getBasePrice();
        final double discountPrice = toughJetResponse.getDiscount();
        final double taxPrice = toughJetResponse.getTax();
        final double total = basePrice + discountPrice + taxPrice;
        ;
        return new BusyFlightsResponse()
                .setDepartureDate(toughJetResponse.getOutboundDateTime())
                .setDestinationAirportCode(toughJetResponse.getDepartureAirportName())
                .setDepartureAirportCode(toughJetResponse.getArrivalAirportName())
                .setArrivalDate(toughJetResponse.getInboundDateTime())
                .setAirline(toughJetResponse.getCarrier())
                .setTotal(total);
    }

}

