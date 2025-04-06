package com.springbootmsq.clientservice.services;

import java.util.List;

import com.springbootmsq.clientservice.dtos.ClientRequestDTO;
import com.springbootmsq.clientservice.dtos.ClientResponseDTO;
import com.springbootmsq.clientservice.dtos.ClientStatsDTO;

public interface ClientService {

  ClientResponseDTO createClient(ClientRequestDTO request);

  List<ClientResponseDTO> getAllClients();

  ClientStatsDTO getClientStats();
}
