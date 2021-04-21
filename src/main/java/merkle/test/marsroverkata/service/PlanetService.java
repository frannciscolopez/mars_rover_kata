package merkle.test.marsroverkata.service;

import org.springframework.stereotype.Service;

import merkle.test.marsroverkata.enums.Direction;
import merkle.test.marsroverkata.model.World;
import merkle.test.marsroverkata.model.Planet;
import merkle.test.marsroverkata.model.Coordinates;
import merkle.test.marsroverkata.model.EmptyPlanet;
import merkle.test.marsroverkata.model.Obstacle;
import merkle.test.marsroverkata.model.Rover;
@Service
public interface PlanetService {
    Rover buildRover(Coordinates startingPoint, Direction directionFacing);
    Obstacle buildObstacle(Coordinates obstacleCoordinate);
    EmptyPlanet buildEmptyPlanet(Coordinates emptyPlanetCoordinates);
    void setRoverInWorld(Rover rover, World world);
    void setObstacleInWorld(Obstacle obstacle, World world);
    void setEmptyPlanetInWorld(Coordinates coordinates, World world);
    Planet updatePlanetCordinates(Planet planet, Coordinates newCoordinates);
}
