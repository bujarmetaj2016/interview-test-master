package com.travix.medusa.busyflights;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.controller.BusyFlightsController;
import com.travix.medusa.busyflights.domain.mapper.DtoMapper;
import com.travix.medusa.busyflights.domain.services.CrazyAirService;
import com.travix.medusa.busyflights.domain.services.ToughjetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BusyFlightsApplicationTests {

    @Autowired
    private BusyFlightsController controller;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CrazyAirService crazyAirService;

    @MockBean
    private ToughjetService toughjetService;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void list() throws Exception {
        final BusyFlightsRequest busyFlightsRequest = new BusyFlightsRequest();
        busyFlightsRequest.setDepartureDate(LocalDate.now().toString());
        busyFlightsRequest.setDestination("FR");
        busyFlightsRequest.setNumberOfPassengers(2);
        busyFlightsRequest.setOrigin("UK");
        busyFlightsRequest.setReturnDate(LocalDate.now().toString());

        DtoMapper dtoMapper = new DtoMapper();
        when(crazyAirService.getCRazyAir(dtoMapper.toCrazyAir(busyFlightsRequest))).thenReturn(new ArrayList());
        when(toughjetService.getToughjet(dtoMapper.toToughJet(busyFlightsRequest))).thenReturn(new ArrayList());

        this.mockMvc.perform(post("/flights")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(busyFlightsRequest))
        )
                .andDo(print()).andExpect(status().isOk());
    }
}
