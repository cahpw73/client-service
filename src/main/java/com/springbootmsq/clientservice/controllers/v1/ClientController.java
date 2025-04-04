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

@RestController
public class ClientController implements ClientApi {

  private final ClientService clientService;

  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @Override
  public ResponseEntity<ClientResponseDTO> createClient(@Valid @RequestBody ClientRequestDTO request) {
    ClientResponseDTO createdClient = clientService.createClient(request);
  
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(createdClient);
  }

  @Override
  public ResponseEntity<List<ClientResponseDTO>> getAllClients() {
    return ResponseEntity.ok(clientService.getAllClients());
  }

  @Override
  public ResponseEntity<ClientStatsDTO> getClientStats() {
    return ResponseEntity.ok(clientService.getClientStats());
  }
}
