package com.betrybe.museumfinder.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@AutoConfigureMockMvc
public class CollectionTypeServiceTest {

  @MockBean
  MuseumFakeDatabase database;

  @Autowired
  CollectionTypeService service;

  @Test
  @DisplayName("1 - Deve retornar pesquisa para /hist")
  void testHistSearch() {

    String[] searchString = new String[]{"hist"};
    CollectionTypeCount collectionTypeCount = new CollectionTypeCount(searchString, 387);

    Mockito
        .when(database.countByCollectionType(any()))
        .thenReturn(collectionTypeCount.count());

    CollectionTypeCount collectionCount = service.countByCollectionTypes(searchString[0]);

    assertEquals(collectionCount.collectionTypes()[0], "hist");
    assertEquals(collectionCount.count(), 387);
  }

  @Test
  @DisplayName("2 - Deve retornar pesquisa para /hist,imag")
  void testHistImagSearch() {
//    String[] searchString = new String[]{"hist"};
//    long count = 492L;
    CollectionTypeCount collectionTypeCount = new CollectionTypeCount(new String[]{"hist"}, 387L);
    CollectionTypeCount collectionTypeCount2 = new CollectionTypeCount(new String[]{"imag"}, 105L);

    Mockito
        .when(database.countByCollectionType(any()))
        .thenReturn(collectionTypeCount.count()).thenReturn(collectionTypeCount2.count());

    CollectionTypeCount collectionCount = service.countByCollectionTypes("hist,imag");

    assertEquals(collectionCount.collectionTypes()[0], "hist");
    assertEquals(collectionCount.collectionTypes()[1], "imag");
    assertEquals(collectionCount.count(), 492);
  }

  @Test
  @DisplayName("3 - Deve retornar contador igual a zero")
  void testSearchZero() {
    String[] searchString = new String[]{};
    long count = 0L;
    CollectionTypeCount collectionTypeCount = new CollectionTypeCount(searchString, count);

    Mockito
        .when(database.countByCollectionType(any()))
        .thenReturn(collectionTypeCount.count());

    CollectionTypeCount collectionCount = service.countByCollectionTypes("");

    assertEquals(collectionCount.collectionTypes()[0], "");
    assertEquals(collectionCount.count(), 0);
  }
}

