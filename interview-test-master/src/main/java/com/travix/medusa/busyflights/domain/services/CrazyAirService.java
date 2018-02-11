package com.travix.medusa.busyflights.domain.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;


@Service
public class CrazyAirService {

    private final String crazyResourceUrl = "http://localhost:8080/crazy_air";
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    @Autowired
    public CrazyAirService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public List<CrazyAirResponse> getCRazyAir(CrazyAirRequest crazyAirRequest) throws IOException {
        final String request = objectMapper.writeValueAsString(crazyAirRequest);
        final ResponseEntity<String> crazyAirResponseResponseEntity
                = restTemplate.postForEntity(crazyResourceUrl, request, String.class);
        final String body = crazyAirResponseResponseEntity.getBody();

        final List<CrazyAirResponse> crazyAirResponses = objectMapper.readValue(body, TypeFactory.defaultInstance().constructArrayType(CrazyAirResponse.class));
        return crazyAirResponses;
    }
}
