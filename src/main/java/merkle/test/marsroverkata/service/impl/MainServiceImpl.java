package merkle.test.marsroverkata.service.impl;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import merkle.test.marsroverkata.enums.Direction;
import merkle.test.marsroverkata.enums.Move;
import merkle.test.marsroverkata.enums.Object;
import merkle.test.marsroverkata.model.World;
import merkle.test.marsroverkata.model.Planet;
import merkle.test.marsroverkata.model.Obstacle;
import merkle.test.marsroverkata.model.Rover;
import merkle.test.marsroverkata.model.Coordinates;
import merkle.test.marsroverkata.model.EmptyPlanet;
import merkle.test.marsroverkata.service.WorldService;
import merkle.test.marsroverkata.service.PlanetService;
import merkle.test.marsroverkata.service.CoordinatesService;
import merkle.test.marsroverkata.service.MainService;
import merkle.test.marsroverkata.utils.Utils;


@Service
public class MainServiceImpl implements MainService{

    @Autowired
    private Utils utils;
    @Autowired
    private PlanetService planetService;
    @Autowired
    private WorldService worldService;
    @Autowired
    private CoordinatesService coordinateService;

    

    @Override
    public void createWorld(Coordinates startingPoint, Direction directionFacing, int worldSize){
        ArrayList<Move> movements = buildMovements();
        World world = this.buildWorld(worldSize);
        this.putObstaclesWorld(world);
        Rover rover = this.putRoverWorld(startingPoint, directionFacing, world);

        this.processMovements(movements, rover, world);
    }

    public ArrayList<Move> buildMovements (){
      ArrayList<Move> movements = new ArrayList<Move>();
      movements.add(Move.RIGHT);
      movements.add (Move.BACKWARD);
      movements.add(Move.FORWARD);
      movements.add(Move.FORWARD);
      movements.add(Move.LEFT);
      movements.add(Move.FORWARD);
      movements.add(Move.FORWARD);
      return movements;
    }

    public World buildWorld(int worldSize){
        World world = this.worldService.createWorld(worldSize);
        this.worldService.initializeWorld(world);
        return world;
    }

    public Rover putRoverWorld(Coordinates startingPoint, Direction directionFacing, World world){
        Rover rover = this.planetService.buildRover(startingPoint,directionFacing);
        this.colocateIfIsPossible(world, startingPoint, rover);
        return rover;
    }

    public void putObstaclesWorld(World world){
        Coordinates obstacleCoordinate = this.utils.getRandomObstacleCoordinates(world);
        Obstacle obstacle = this.planetService.buildObstacle(obstacleCoordinate);
        this.planetService.setObstacleInWorld(obstacle, world);
    }
    
    public void processMovements(ArrayList<Move> movements, Rover rover, World world){
        for(int i = 0; i < movements.size(); i++){
          this.moveRove(movements.get(i), rover, world);
          System.out.print("[ Rover information: Nº movement: "+i+", Coordinates: {"+rover.getCoordinates().getRow()+","+rover.getCoordinates().getColumn()+ "}, Movement type: " +movements.get(i).toString()+"]\n");
          this.utils.printWorld(world);
        }
    }

    public void moveRove(Move movement, Rover rover, World world ){
        Coordinates coordinates = new Coordinates(); 
            switch(movement) {
                case LEFT:
                    coordinates = this.turnLeft(rover);
                  break;
                case RIGHT:
                    coordinates = this.turnRight(rover);
                  break;
                case BACKWARD:
                    coordinates = this.moveBackward(rover);
                  break;
                case FORWARD:
                    coordinates = this.moveForward(rover);
                  break;
              }
              this.coordinateService.turnPlanetIfNecessary(coordinates, world);
              this.colocateIfIsPossible(world, coordinates, rover);

    }

    public void colocateIfIsPossible(World world, Coordinates coordinates, Rover rover){
      boolean movementIsPossible = this.coordinateService.movementIsPossible(world, coordinates);
      if(movementIsPossible){
        this.planetService.setEmptyPlanetInWorld(rover.getCoordinates(), world);
        this.planetService.updatePlanetCordinates(rover, coordinates);
        this.planetService.setRoverInWorld(rover, world);
      }else{
          System.out.println("This movement is not possible because there is an obstacle in the position: ["+coordinates.getRow()+", "+coordinates.getColumn()+"]");
          System.exit(0);
      }
    }


    public Coordinates turnLeft(Rover rover){
        Coordinates coordinates = new Coordinates();
        Coordinates roverCoordinates = rover.getCoordinates();
        switch(rover.getDirectionFacing()) {
            case N:
                coordinates = this.coordinateService.subCoordinatesRow(roverCoordinates);
              break;
            case S:
                coordinates = this.coordinateService.addCoordinatesRow(roverCoordinates);
              break;
            case E:
                coordinates = this.coordinateService.addCoordinatesColumn(roverCoordinates);
              break;
            case W:
                coordinates = this.coordinateService.subCoordinatesColumn(roverCoordinates);
              break;
          } 
          return coordinates;
    }

    public Coordinates turnRight(Rover rover){
        Coordinates coordinates = new Coordinates();
        Coordinates roverCoordinates = rover.getCoordinates();
        switch(rover.getDirectionFacing()) {
            case N:
                coordinates = this.coordinateService.addCoordinatesRow(roverCoordinates);
              break;
            case S:
                coordinates = this.coordinateService.subCoordinatesRow(roverCoordinates);
              break;
            case E:
                coordinates = this.coordinateService.subCoordinatesColumn(roverCoordinates);
              break;
            case W:
                coordinates = this.coordinateService.addCoordinatesColumn(roverCoordinates);
              break;
          }  
            return coordinates; 
    }

    public Coordinates moveForward(Rover rover){
        Coordinates coordinates = new Coordinates();
        Coordinates roverCoordinates = rover.getCoordinates();
        switch(rover.getDirectionFacing()) {
            case N:
                coordinates = this.coordinateService.addCoordinatesColumn(roverCoordinates);
              break;
            case S:
                coordinates = this.coordinateService.subCoordinatesColumn(roverCoordinates);
              break;
            case E:
                coordinates = this.coordinateService.addCoordinatesRow(roverCoordinates);
              break;
            case W:
                coordinates = this.coordinateService.subCoordinatesRow(roverCoordinates);
              break;
          }  
        return coordinates;
    }

    public Coordinates moveBackward(Rover rover){
        Coordinates coordinates = new Coordinates();
        Coordinates roverCoordinates = rover.getCoordinates();
        switch(rover.getDirectionFacing()) {
            case N:
                coordinates = this.coordinateService.subCoordinatesColumn(roverCoordinates);                
              break;
            case S:
                coordinates = this.coordinateService.addCoordinatesColumn(roverCoordinates);              
              break;
            case E:
                coordinates = this.coordinateService.subCoordinatesRow(roverCoordinates);            
              break;
            case W:
                coordinates = this.coordinateService.addCoordinatesRow(roverCoordinates);          
              break;
          }  
          return coordinates;
    }
}
