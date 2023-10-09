package com.betrybe.museumfinder.solution;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
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
  @DisplayName("1 - Deve retornar um museu pelo id na camada controller")
  void testFindByIdController() throws Exception {
    Museum museum = new Museum();

    museum.setName("Museu de Artes");
    museum.setCoordinate(new Coordinate(100, 200));
    museum.setAddress("Rua dos Museus");
    museum.setDescription("Museu com quadros e esculturas");
    museum.setCollectionType("quadros, esculturas");
    museum.setSubject("Arte em geral");
    museum.setUrl("www.museudeartes.com");
    museum.setId(1L);

    Mockito
        .when(service.getMuseum(1L))
        .thenReturn(museum);

    String url = "/museums/1";

    mockMvc.perform(
        get(url)).andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(museum.getId()))
        .andExpect(jsonPath("$.name").value(museum.getName()))
        .andExpect(jsonPath("$.address").value(museum.getAddress()))
        .andExpect(jsonPath("$.description").value(museum.getDescription()))
        .andExpect(jsonPath("$.url").value(museum.getUrl()))
        .andExpect(jsonPath("$.subject").value(museum.getSubject()))
        .andExpect(jsonPath("$.collectionType").value(museum.getCollectionType()));
  }
}
