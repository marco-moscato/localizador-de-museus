package com.betrybe.museumfinder.service;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.util.CoordinateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * camada service de Museum.
 */
@Service
public class MuseumService implements MuseumServiceInterface {
  MuseumFakeDatabase database;

  @Autowired
  public MuseumService(MuseumFakeDatabase database) {
    this.database = database;
  }

  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double maxDistance) {
    boolean isCoordinateValid = CoordinateUtil.isCoordinateValid(coordinate);
    if (!isCoordinateValid) {
      throw new InvalidCoordinateException();
    }
    return database.getClosestMuseum(coordinate, maxDistance)
        .orElseThrow(MuseumNotFoundException::new);
  }

  @Override
  public Museum createMuseum(Museum museum) {
    boolean isCoordinateValid = CoordinateUtil.isCoordinateValid(museum.getCoordinate());
    if (!isCoordinateValid) {
      throw new InvalidCoordinateException();
    }
    return database.saveMuseum(museum);
  }

  @Override
  public Museum getMuseum(Long id) {
    return null;
  }
}
