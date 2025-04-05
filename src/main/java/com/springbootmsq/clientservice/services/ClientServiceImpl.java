package com.springbootmsq.clientservice.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springbootmsq.clientservice.dtos.ClientRequestDTO;
import com.springbootmsq.clientservice.dtos.ClientResponseDTO;
import com.springbootmsq.clientservice.dtos.ClientStatsDTO;
import com.springbootmsq.clientservice.entities.Client;
import com.springbootmsq.clientservice.exceptions.BusinessException;
import com.springbootmsq.clientservice.repositories.ClientRepository;
import com.springbootmsq.clientservice.utils.DateUtil;
import com.springbootmsq.clientservice.utils.LifeExpectancyUtil;
import com.springbootmsq.clientservice.utils.StatisticsUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

  private final ClientRepository clientRepository;

  public ClientServiceImpl(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Override
  public ClientResponseDTO createClient(ClientRequestDTO request) {
    log.info("Creating new client: {} {}", request.getFirstName(), request.getLastName());

    DateUtil.validateAgeMatchesBirthDate(request.getAge(), request.getBirthDate());
    validateClientUniqueness(request.getFirstName(), request.getLastName(), request.getAge());

    Client client = Client.builder()
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .age(request.getAge())
        .birthDate(request.getBirthDate())
        .build();

    client = clientRepository.save(client);
    log.info("Client saved with ID: {}", client.getId());

    return toDto(client);
  }

  @Override
  public List<ClientResponseDTO> getAllClients() {
    log.debug("Fetching all clients from the database");
    List<Client> clients = clientRepository.findAll();
    log.info("Number of clients retrieved: {}", clients.size());
    return clients.stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }

  private ClientResponseDTO toDto(Client client) {
    ClientResponseDTO dto = ClientResponseDTO.builder()
            .id(client.getId())
            .firstName(client.getFirstName())
            .lastName(client.getLastName())
            .age(client.getAge())
            .birthDate(client.getBirthDate())
            .lifeExpectancy(LifeExpectancyUtil.generateLifeExpectancy())
            .build();
    log.debug("Mapped client to DTO: {}", dto);
    return dto;
  }

  @Override
  public ClientStatsDTO getClientStats() {
    log.info("Calculating client statistics");
    List<Client> clients = clientRepository.findAll();

    if (clients.isEmpty()) {
        log.warn("No clients found. Returning default statistics.");
        return ClientStatsDTO.builder()
                .averageAge(StatisticsUtil.DEFAULT_AVERAGE_AGE)
                .standardDeviation(StatisticsUtil.DEFAULT_STANDARD_DEVIATION)
                .build();
    }

    List<Integer> ages = clients.stream()
            .map(Client::getAge)
            .toList();

    double average = StatisticsUtil.calculateAverage(ages);
    double stdDev = StatisticsUtil.calculateStandardDeviation(ages, average);

    log.info("Calculated average age: {}, standard deviation: {}", average, stdDev);

    return ClientStatsDTO.builder()
            .averageAge(StatisticsUtil.round(average))
            .standardDeviation(StatisticsUtil.round(stdDev))
            .build();
  }

  private void validateClientUniqueness(String firstName, String lastName, int age) {
    log.debug("Validating uniqueness for client: {} {} (age: {})", firstName, lastName, age);
    boolean exists = clientRepository.existsByNameAndAge(firstName, lastName, age);
    if (exists) {
        log.error("Duplicate client detected: {} {} (age: {})", firstName, lastName, age);
        throw new BusinessException("A client with the same name and age already exists.");
    }
  }
}
