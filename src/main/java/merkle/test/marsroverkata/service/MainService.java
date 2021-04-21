package merkle.test.marsroverkata.service;

import java.util.ArrayList;

import merkle.test.marsroverkata.enums.Move;
import merkle.test.marsroverkata.model.Coordinates;
import merkle.test.marsroverkata.enums.Direction;

public interface MainService {
    void createWorld(Coordinates startingPoint, Direction directionFacing, int worldSize);
}
