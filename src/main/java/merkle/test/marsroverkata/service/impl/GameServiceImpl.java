package merkle.test.marsroverkata.service.impl;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import merkle.test.marsroverkata.enums.Direction;
import merkle.test.marsroverkata.enums.Move;
import merkle.test.marsroverkata.enums.Object;
import merkle.test.marsroverkata.model.Board;
import merkle.test.marsroverkata.model.Cell;
import merkle.test.marsroverkata.model.Obstacle;
import merkle.test.marsroverkata.model.Rover;
import merkle.test.marsroverkata.model.Coordinates;
import merkle.test.marsroverkata.model.EmptyCell;
import merkle.test.marsroverkata.service.BoardService;
import merkle.test.marsroverkata.service.CellService;
import merkle.test.marsroverkata.service.CoordinatesService;
import merkle.test.marsroverkata.service.GameService;
import merkle.test.marsroverkata.utils.Utils;


@Service
public class GameServiceImpl implements GameService{

    @Autowired
    private Utils utils;
    @Autowired
    private CellService cellService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private CoordinatesService coordinateService;

    ArrayList<Move> movements = new ArrayList<Move>();
    @Override
    public void startGame(Coordinates startingPoint, Direction directionFacing, int boardSize){
        Board board = this.buildBoard(boardSize);
        this.colocateObstaclesBoard(board);
        Rover rover = this.colocateRoverBoard(startingPoint, directionFacing, board);
        movements.add(Move.RIGHT);
        movements.add (Move.BACKWARD);
        movements.add(Move.FORWARD);
        movements.add(Move.FORWARD);
        movements.add(Move.LEFT);
        this.processMovements(movements, rover, board);
    }

    public Board buildBoard(int boardSize){
        Board board = this.boardService.createBoard(boardSize);
        this.boardService.intializeBoard(board);
        return board;
    }

    public Rover colocateRoverBoard(Coordinates startingPoint, Direction directionFacing, Board board){
        Rover rover = this.cellService.buildRover(startingPoint,directionFacing);
        this.cellService.setRoverInBoard(rover, board);
        return rover;
    }

    public void colocateObstaclesBoard(Board board){
        Coordinates obstacleCoordinate = this.utils.getRandomObstacleCoordinates(board);
        Obstacle obstacle = this.cellService.buildObstacle(obstacleCoordinate);
        this.cellService.setObstacleInBoard(obstacle, board);
    }
    
    public void processMovements(ArrayList<Move> movements, Rover rover, Board board){
        for(int i = 0; i < movements.size(); i++){
          this.moveRove(movements.get(i), rover, board);
          System.out.print("[ Rover information: Nº movement: "+i+", Coordinates: {"+rover.getCoordinates().getRow()+","+rover.getCoordinates().getColumn()+ "}, Movement type: " +movements.get(i).toString()+"]\n");
          this.utils.printBoard(board);
        }
    }

    public void moveRove(Move movement, Rover rover, Board board ){
        Coordinates coordinates = new Coordinates(); 
            switch(movement) {
                case LEFT:
                    coordinates = this.turnLeft(rover, board);
                  break;
                case RIGHT:
                    coordinates = this.turnRight(rover,board);
                  break;
                case BACKWARD:
                    coordinates = this.moveBackward(rover,board);
                  break;
                case FORWARD:
                    coordinates = this.moveForward(rover,board);
                  break;
              }
              this.coordinateService.turnPlanetIfNecessary(coordinates, board);
              boolean movementIsPossible = this.coordinateService.movementIsPossible(board, coordinates);
              if(movementIsPossible){
                this.cellService.setEmptyCellInBoard(rover.getCoordinates(), board);
                this.cellService.updateCellCordinates(rover, coordinates);
                this.cellService.setRoverInBoard(rover, board);
              }else{
                  System.out.println("This movement is not possible because there is an obstacle in the position: ["+coordinates.getRow()+", "+coordinates.getColumn()+"]");
              }
    }


    public Coordinates turnLeft(Rover rover, Board board){
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

    public Coordinates turnRight(Rover rover, Board board){
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

    public Coordinates moveForward(Rover rover, Board board){
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

    public Coordinates moveBackward(Rover rover, Board board){
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
