package com.betrybe.museumfinder.solution;

import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CollectionTypeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CollectionTypeService service;

  @Test
  @DisplayName("1 - Deve retornar pesquisa para /hist")
  void testHistSearch() throws Exception {

    CollectionTypeCount collectionTypeCountMock = new CollectionTypeCount(new String[]{"hist"}, 387L);

    Mockito
        .when(service.countByCollectionTypes("hist"))
        .thenReturn(collectionTypeCountMock);

    String url = "/collections/count/hist";
    mockMvc.perform(
        get(url)).andExpect(status().isOk())
        .andExpect(jsonPath("$.collectionTypes").value("hist"))
        .andExpect(jsonPath("$.count").value(387));
  }

  @Test
  @DisplayName("2 - Deve retornar pesquisa para /hist, imag")
  void testHistImagSearch() throws Exception {

    CollectionTypeCount collectionTypeCountMock = new CollectionTypeCount(new String[]{"hist,imag"}, 492L);

    Mockito
        .when(service.countByCollectionTypes("hist,imag"))
        .thenReturn(collectionTypeCountMock);

    String url = "/collections/count/hist,imag";
    mockMvc.perform(
            get(url)).andExpect(status().isOk())
        .andExpect(jsonPath("$.collectionTypes").value("hist,imag"))
        .andExpect(jsonPath("$.count").value(492));
  }
}
