package merkle.test.marsroverkata.service;

import java.util.ArrayList;

import merkle.test.marsroverkata.enums.Move;
import merkle.test.marsroverkata.model.Coordinate;
import merkle.test.marsroverkata.enums.Direction;

public interface GameService {
    void startGame(Coordinate startingPoint, Direction directionFacing, int boardSize);
    void processMovements(ArrayList<Move> movements);
}
