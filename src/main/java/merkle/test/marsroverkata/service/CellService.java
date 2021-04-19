package merkle.test.marsroverkata.service;

import org.springframework.stereotype.Service;

import merkle.test.marsroverkata.enums.Direction;
import merkle.test.marsroverkata.model.Board;
import merkle.test.marsroverkata.model.Cell;
import merkle.test.marsroverkata.model.Coordinates;
import merkle.test.marsroverkata.model.EmptyCell;
import merkle.test.marsroverkata.model.Obstacle;
import merkle.test.marsroverkata.model.Rover;
@Service
public interface CellService {
    Rover buildRover(Coordinates startingPoint, Direction directionFacing);
    Obstacle buildObstacle(Coordinates obstacleCoordinate);
    EmptyCell buildEmptyCell(Coordinates emptyCellCoordinates);
    void setRoverInBoard(Rover rover, Board board);
    void setObstacleInBoard(Obstacle obstacle, Board board);
    void setEmptyCellInBoard(Coordinates coordinates, Board board);
    Cell updateCellCordinates(Cell cell, Coordinates newCoordinates);
}
