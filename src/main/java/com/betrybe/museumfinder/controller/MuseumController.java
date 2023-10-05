package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MuseumController.
 */
@RestController
@RequestMapping("/museums")
public class MuseumController {
  MuseumServiceInterface service;

  @Autowired
  public MuseumController(MuseumServiceInterface service) {
    this.service = service;
  }

  /**
   * metódo salva um novo museu.
   *
   * @param museumDto recebe um objeto do tipo museumDto
   * @return um objeto do tipo museumDto
   */
  @PostMapping
  public ResponseEntity<MuseumDto> saveMuseum(@RequestBody MuseumCreationDto museumDto) {
    Museum museum = new Museum();

    museum.setName(museumDto.name());
    museum.setCoordinate(museumDto.coordinate());
    museum.setAddress(museumDto.address());
    museum.setDescription(museumDto.description());
    museum.setSubject(museumDto.subject());
    museum.setUrl(museumDto.url());
    museum.setCollectionType(museumDto.collectionType());

    Museum newMuseum = service.createMuseum(museum);

    MuseumDto newMuseumDto = new MuseumDto(
        newMuseum.getId(),
        newMuseum.getName(),
        newMuseum.getDescription(),
        newMuseum.getAddress(),
        newMuseum.getCollectionType(),
        newMuseum.getSubject(),
        newMuseum.getUrl(),
        newMuseum.getCoordinate()
    );

    return ResponseEntity.status(HttpStatus.CREATED).body(newMuseumDto);
  }
}
