package merkle.test.marsroverkata.service;

import org.springframework.stereotype.Service;

import merkle.test.marsroverkata.model.World;
import merkle.test.marsroverkata.model.Coordinates;
import merkle.test.marsroverkata.model.Rover;

@Service
public interface CoordinatesService {
  Coordinates addCoordinatesRow(Coordinates coordinates); 
  Coordinates addCoordinatesColumn(Coordinates coordinates);
  Coordinates subCoordinatesRow(Coordinates coordinates);
  Coordinates subCoordinatesColumn(Coordinates coordinates);
  boolean movementIsPossible(World world, Coordinates possibleCoordinate);
  Coordinates buildCoordinates(int row, int column);
  Coordinates turnPlanetIfNecessary(Coordinates coordinates, World world);
}
