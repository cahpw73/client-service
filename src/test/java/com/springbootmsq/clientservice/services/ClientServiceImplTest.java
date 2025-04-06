package com.springbootmsq.clientservice.services;

import com.springbootmsq.clientservice.dtos.ClientRequestDTO;
import com.springbootmsq.clientservice.dtos.ClientResponseDTO;
import com.springbootmsq.clientservice.dtos.ClientStatsDTO;
import com.springbootmsq.clientservice.entities.Client;
import com.springbootmsq.clientservice.exceptions.BusinessException;
import com.springbootmsq.clientservice.repositories.ClientRepository;
import com.springbootmsq.clientservice.utils.StatisticsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceImplTest {

  @Mock
  private ClientRepository clientRepository;

  @InjectMocks
  private ClientServiceImpl clientService;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldCreateClientSuccessfully() {
    ClientRequestDTO request = ClientRequestDTO.builder()
        .firstName("Ana")
        .lastName("Lopez")
        .age(30)
        .birthDate(LocalDate.now().minusYears(30))
        .build();

    Client saved = Client.builder()
        .id(1L)
        .firstName("Ana")
        .lastName("Lopez")
        .age(30)
        .birthDate(request.getBirthDate())
        .build();

    when(clientRepository.existsByNameAndAge("Ana", "Lopez", 30)).thenReturn(false);
    when(clientRepository.save(any(Client.class))).thenReturn(saved);

    ClientResponseDTO result = clientService.createClient(request);

    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo(1L);
    assertThat(result.getFirstName()).isEqualTo("Ana");
  }

  @Test
  void shouldThrowExceptionWhenClientIsDuplicate() {
    ClientRequestDTO request = ClientRequestDTO.builder()
        .firstName("Pedro")
        .lastName("Sosa")
        .age(40)
        .birthDate(LocalDate.now().minusYears(40))
        .build();

    when(clientRepository.existsByNameAndAge("Pedro", "Sosa", 40)).thenReturn(true);

    assertThatThrownBy(() -> clientService.createClient(request))
        .isInstanceOf(BusinessException.class)
        .hasMessageContaining("already exists");
  }

  @Test
  void shouldReturnClientStatsCorrectly() {
    Client c1 = Client.builder().age(30).build();
    Client c2 = Client.builder().age(40).build();
    Client c3 = Client.builder().age(50).build();

    when(clientRepository.findAll()).thenReturn(List.of(c1, c2, c3));

    ClientStatsDTO stats = clientService.getClientStats();

    assertThat(stats.getAverageAge()).isEqualTo(40.0);
    assertThat(stats.getStandardDeviation()).isGreaterThan(0.0);
  }

  @Test
  void shouldReturnZeroStatsIfNoClientsExist() {
    when(clientRepository.findAll()).thenReturn(List.of());

    ClientStatsDTO stats = clientService.getClientStats();

    assertThat(stats.getAverageAge()).isEqualTo(StatisticsUtil.DEFAULT_AVERAGE_AGE);
    assertThat(stats.getStandardDeviation()).isEqualTo(StatisticsUtil.DEFAULT_STANDARD_DEVIATION);
  }
}
