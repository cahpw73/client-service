package com.springbootmsq.clientservice.controllers.v1.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springbootmsq.clientservice.dtos.ApiError;
import com.springbootmsq.clientservice.dtos.ClientRequestDTO;
import com.springbootmsq.clientservice.dtos.ClientResponseDTO;
import com.springbootmsq.clientservice.dtos.ClientStatsDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Clients V1", description = "Client management V1 API")
@RequestMapping("/v1/api/clients")
public interface ClientApi {

  @Operation(summary = "Register a new client")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Client created successfully"),
      @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content(schema = @Schema(implementation = ApiError.class))),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ApiError.class)))
  })

  @PostMapping
  public ResponseEntity<ClientResponseDTO> createClient(@Valid @RequestBody ClientRequestDTO request);

  @Operation(summary = "Get all clients with derived info")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Clients retrieved successfully"),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ApiError.class)))
  })

  @GetMapping
  public ResponseEntity<List<ClientResponseDTO>> getAllClients();

  @Operation(summary = "Get client age statistics")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Client statistics retrieved successfully"),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ApiError.class)))
  })

  @GetMapping("/stats")
  ResponseEntity<ClientStatsDTO> getClientStats();
}
