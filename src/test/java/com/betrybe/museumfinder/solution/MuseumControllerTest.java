package com.betrybe.museumfinder.solution;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import org.apache.el.stream.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MuseumControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MuseumServiceInterface service;

  @Test
  @DisplayName("1 - Deve retornar os dados de um museu quando procurado pelo id")
  public void testFindByIdControllerIsOK() throws Exception {
    Museum museum = new Museum();
    Coordinate coordinate = new Coordinate(100, 200);

    museum.setName("Museu de Artes");
    museum.setCoordinate(coordinate);
    museum.setAddress("Rua dos Museus");
    museum.setDescription("Museu com quadros e esculturas");
    museum.setCollectionType("quadros, esculturas");
    museum.setSubject("Arte em geral");
    museum.setUrl("www.museudeartes.com");
    museum.setId(1L);

    Mockito
        .when(service.getMuseum(museum.getId()))
        .thenReturn(museum);

    String url = "/museums/1";

    mockMvc.perform(
        get(url)).andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value(museum.getName()))
        .andExpect(jsonPath("$.address").value(museum.getAddress()))
        .andExpect(jsonPath("$.description").value(museum.getDescription()))
        .andExpect(jsonPath("$.url").value(museum.getUrl()))
        .andExpect(jsonPath("$.subject").value(museum.getSubject()))
        .andExpect(jsonPath("$.collectionType").value(museum.getCollectionType()))
        .andExpect(jsonPath("$.coordinate.latitude").value(museum.getCoordinate().latitude()))
        .andExpect(jsonPath("$.coordinate.longitude").value(museum.getCoordinate().longitude()));
  }

  @Test
  @DisplayName("2 - Deve lançar uma exceção quando um id não for encontrado")
  public void testFindByIdControllerNotFound() throws Exception {
    Mockito
        .when(service.getMuseum(any()))
        .thenThrow(MuseumNotFoundException.class);

    String url = "/museums/999";
    mockMvc.perform(get(url))
        .andExpect(status().isNotFound());
  }
}
