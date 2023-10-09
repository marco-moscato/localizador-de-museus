package com.betrybe.museumfinder.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class MuseumServiceTest {

  @MockBean
  MuseumFakeDatabase database;

  @Autowired
  MuseumServiceInterface service;

  @Test
  @DisplayName("1 - Deve retornar um museu pelo id na camada service")
  void testFindByIdService() throws Exception {
    Museum museum = new Museum();

    museum.setName("Museu de Artes");
    museum.setCoordinate(new Coordinate(10.1, 10.1));
    museum.setAddress("Rua dos Museus");
    museum.setDescription("Museu com quadros e esculturas");
    museum.setCollectionType("quadros, esculturas");
    museum.setSubject("Arte em geral");
    museum.setUrl("www.museudeartes.com");
    museum.setId(1L);

    Mockito
        .when(database.getMuseum(any()))
        .thenReturn(Optional.of(museum));

    Long id = museum.getId();

    Museum mockMuseum = service.getMuseum(id);

    assertNotNull(mockMuseum);

    assertEquals(museum.getId(), mockMuseum.getId());
    assertEquals(museum.getCoordinate(), mockMuseum.getCoordinate());
    assertEquals(museum.getName(), mockMuseum.getName());
    assertEquals(museum.getAddress(), mockMuseum.getAddress());
    assertEquals(museum.getUrl(), mockMuseum.getUrl());
    assertEquals(museum.getCollectionType(), mockMuseum.getCollectionType());
    assertEquals(museum.getDescription(), museum.getDescription());
  }
}
