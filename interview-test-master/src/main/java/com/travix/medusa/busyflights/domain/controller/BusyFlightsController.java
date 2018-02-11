package com.travix.medusa.busyflights.domain.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.services.MergeResultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/flights")
public class BusyFlightsController {
    private final MergeResultService mergeResultService;

    public BusyFlightsController(MergeResultService mergeResultService) {
        this.mergeResultService = mergeResultService;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity getBusyFlights(@Valid @RequestBody BusyFlightsRequest request) {

        final List<BusyFlightsResponse> busyFlightsResponses;
        try {
            busyFlightsResponses = mergeResultService.callAndMergeResult(request);
        } catch (IOException e) {
            return new ResponseEntity<String>("Some error occurred at server", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(busyFlightsResponses);
    }
}
