package com.springbootmsq.clientservice.controllers.v1;

import com.springbootmsq.clientservice.dtos.ClientRequestDTO;
import com.springbootmsq.clientservice.dtos.ClientResponseDTO;
import com.springbootmsq.clientservice.dtos.ClientStatsDTO;
import com.springbootmsq.clientservice.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientControllerTest {

  private ClientService clientService;
  private ClientController clientController;

  @BeforeEach
  void setUp() {
    clientService = mock(ClientService.class);
    clientController = new ClientController(clientService);
  }

  @Test
  void testCreateClient() {
    ClientRequestDTO requestDTO = new ClientRequestDTO();
    requestDTO.setFirstName("John");
    requestDTO.setLastName("Doe");
    requestDTO.setAge(30);

    ClientResponseDTO responseDTO = new ClientResponseDTO();
    responseDTO.setId(1L);
    responseDTO.setFirstName("John");
    responseDTO.setLastName("Doe");
    responseDTO.setAge(30);

    when(clientService.createClient(ArgumentMatchers.any(ClientRequestDTO.class))).thenReturn(responseDTO);

    ResponseEntity<ClientResponseDTO> response = clientController.createClient(requestDTO);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(1L, response.getBody().getId());
    assertEquals("John", response.getBody().getFirstName());
  }

  @Test
  void testGetAllClients() {
    ClientResponseDTO client1 = new ClientResponseDTO();
    client1.setId(1L);
    client1.setFirstName("Alice");
    client1.setLastName("Smith");
    client1.setAge(25);

    ClientResponseDTO client2 = new ClientResponseDTO();
    client2.setId(2L);
    client2.setFirstName("Bob");
    client2.setLastName("Brown");
    client2.setAge(32);

    List<ClientResponseDTO> mockClients = Arrays.asList(client1, client2);
    when(clientService.getAllClients()).thenReturn(mockClients);

    ResponseEntity<List<ClientResponseDTO>> response = clientController.getAllClients();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(2, response.getBody().size());
    assertEquals("Alice", response.getBody().get(0).getFirstName());
  }

  @Test
  void testGetClientStats() {
    ClientStatsDTO statsDTO = new ClientStatsDTO();
    statsDTO.setAverageAge(29.5);
    statsDTO.setStandardDeviation(3.5);

    when(clientService.getClientStats()).thenReturn(statsDTO);

    ResponseEntity<ClientStatsDTO> response = clientController.getClientStats();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(29.5, response.getBody().getAverageAge());
    assertEquals(3.5, response.getBody().getStandardDeviation());
  }
}
