package com.travix.medusa.busyflights.domain.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class ToughjetService {
    private final String toughjetResourceUrl = "http://localhost:8080/tough_jet";
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    @Autowired
    public ToughjetService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public List<ToughJetResponse> getToughjet(ToughJetRequest toughJetRequest) throws IOException {
        final String request = objectMapper.writeValueAsString(toughJetRequest);
        final ResponseEntity<String> toughJetResponseResponseEntity
                = restTemplate.postForEntity(toughjetResourceUrl, request, String.class);
        final String body = toughJetResponseResponseEntity.getBody();

        final List<ToughJetResponse> toughJetResponses = objectMapper.readValue(body, TypeFactory.defaultInstance().constructArrayType(ToughJetResponse.class));
        return toughJetResponses;
    }
}

