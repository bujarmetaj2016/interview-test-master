package com.travix.medusa.busyflights.domain.services;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.mapper.DtoMapper;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MergeResultService {

    private final CrazyAirService crazyAirService;
    private final ToughjetService toughjetService;
    private final DtoMapper dtoMapper;

    public MergeResultService(CrazyAirService crazyAirService, ToughjetService toughjetService, DtoMapper dtoMapper) {
        this.crazyAirService = crazyAirService;
        this.toughjetService = toughjetService;
        this.dtoMapper = dtoMapper;
    }

    public List<BusyFlightsResponse> callAndMergeResult(BusyFlightsRequest busyFlightsRequest) throws IOException {
        final CrazyAirRequest crazyAirRequest = dtoMapper.toCrazyAir(busyFlightsRequest);
        final ToughJetRequest toughJetRequest = dtoMapper.toToughJet(busyFlightsRequest);

        final List<CrazyAirResponse> crazyAirResponseList = crazyAirService.getCRazyAir(crazyAirRequest);
        final List<ToughJetResponse> toughJetResponseList = toughjetService.getToughjet(toughJetRequest);

        final List<BusyFlightsResponse> listFromCrazyAir = crazyAirResponseList.stream().map(dtoMapper::fromCrazyAir).collect(Collectors.toList());
        final List<BusyFlightsResponse> listFromBusyFlights = toughJetResponseList.stream().map(dtoMapper::fromToughJet).collect(Collectors.toList());

        return Stream.concat(listFromCrazyAir.stream(), listFromBusyFlights.stream()).distinct().collect(Collectors.toList());
    }

}
