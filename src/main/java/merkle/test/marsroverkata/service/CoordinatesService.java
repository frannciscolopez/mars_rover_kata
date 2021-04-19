package merkle.test.marsroverkata.service;

import org.springframework.stereotype.Service;

import merkle.test.marsroverkata.model.Board;
import merkle.test.marsroverkata.model.Coordinates;
import merkle.test.marsroverkata.model.Rover;

@Service
public interface CoordinatesService {
  Coordinates addCoordinatesRow(Coordinates coordinates); 
  Coordinates addCoordinatesColumn(Coordinates coordinates);
  Coordinates subCoordinatesRow(Coordinates coordinates);
  Coordinates subCoordinatesColumn(Coordinates coordinates);
  boolean movementIsPossible(Board board, Coordinates possibleCoordinate);
  Coordinates buildCoordinates(int row, int column);
  Coordinates turnPlanetIfNecessary(Coordinates coordinates, Board board);
}
