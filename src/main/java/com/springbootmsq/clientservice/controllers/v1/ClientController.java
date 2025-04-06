package com.springbootmsq.clientservice.controllers.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springbootmsq.clientservice.controllers.v1.api.ClientApi;
import com.springbootmsq.clientservice.dtos.ClientRequestDTO;
import com.springbootmsq.clientservice.dtos.ClientResponseDTO;
import com.springbootmsq.clientservice.dtos.ClientStatsDTO;
import com.springbootmsq.clientservice.services.ClientService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ClientController implements ClientApi {

  private final ClientService clientService;

  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @Override
  public ResponseEntity<ClientResponseDTO> createClient(@Valid @RequestBody ClientRequestDTO request) {
    log.info("Received POST /clients request: {}", request);
    ClientResponseDTO createdClient = clientService.createClient(request);
    log.info("Client successfully created with ID: {}", createdClient.getId());

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(createdClient);
  }

  @Override
  public ResponseEntity<List<ClientResponseDTO>> getAllClients() {
    log.info("Received GET /clients request");
    List<ClientResponseDTO> clients = clientService.getAllClients();
    log.info("Returning {} clients", clients.size());

    return ResponseEntity.ok(clients);
  }

  @Override
  public ResponseEntity<ClientStatsDTO> getClientStats() {
    log.info("Received GET /clients/stats request");
    ClientStatsDTO stats = clientService.getClientStats();
    log.info("Returning client statistics: averageAge={}, standardDeviation={}", stats.getAverageAge(),
        stats.getStandardDeviation());

    return ResponseEntity.ok(stats);
  }
}
