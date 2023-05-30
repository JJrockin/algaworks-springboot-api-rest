package com.jjrockin.spring.dive.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jjrockin.spring.dive.domain.model.Client;
import com.jjrockin.spring.dive.domain.repository.ClientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ClientControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    ClientRepository repository;

    @BeforeEach
    void setUp() {
        Client client = new Client();
        client.setName("Joana");
        client.setEmail("joana@email.com");
        client.setTelephone("81 98156-8769");
        repository.save(client);
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Lists all clients")
    void listAllClients() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clients"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Find client by Id")
    void searchById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clients/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Add a new Client")
    void addNewClient() throws Exception {
        Client client = new Client();
        client.setName("Joaquim");
        client.setEmail("joaquim@email.com");
        client.setTelephone("81 98456-9865");

        String clientBody = mapper.writeValueAsString(client);

        mockMvc.perform(MockMvcRequestBuilders.post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(clientBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Update Client Registry")
    void updateClientRegistry() throws Exception {
        Client client = new Client();
        client.setName("Vitoria");
        client.setEmail("vic@email.com");
        client.setTelephone("81 99986-9865");

        String clientBody = mapper.writeValueAsString(client);

        mockMvc.perform(MockMvcRequestBuilders.put("/clients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(clientBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Response not found")
    void updateClientNotFound() throws Exception {

        Client client = new Client();
        client.setName("Vitoria");
        client.setEmail("vic@email.com");
        client.setTelephone("81 99986-9865");

        String clientBody = mapper.writeValueAsString(client);

        mockMvc.perform(MockMvcRequestBuilders.put("/clients/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(clientBody))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Delete client")
    void deleteClientRegistry() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/clients/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }
}