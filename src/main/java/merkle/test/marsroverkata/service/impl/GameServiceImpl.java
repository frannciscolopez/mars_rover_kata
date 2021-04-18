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
import merkle.test.marsroverkata.model.Coordinate;
import merkle.test.marsroverkata.model.EmptyCell;
import merkle.test.marsroverkata.service.GameService;
import merkle.test.marsroverkata.utils.Utils;


@Service
public class GameServiceImpl implements GameService{

    @Autowired
    private Utils utils;
    
    ArrayList<Move> movements = new ArrayList<Move>();

    @Override
    public void startGame(Coordinate startingPoint, Direction directionFacing, int boardSize){
        Board board = this.utils.buildBoard(boardSize);
        board = this.intializeBoard(board);
        //this.printBoard(board);
        Rover rover = this.buildRover(startingPoint,directionFacing);
        this.setRoverPosition(rover, board);
        Coordinate s = new Coordinate();
        s.setColumn(1);
        s.setRow(2);
        Obstacle obstacle = this.buildObstacle(s);
        this.setObstaclePosition(obstacle, board);
        movements.add(Move.RIGHT);
        movements.add (Move.BACKWARD);
        this.moveRove(movements, rover, board);

    }
    
    public void processMovements(ArrayList<Move> movements){
        for(int i = 0; i < movements.size(); i++){
          
            
        }

    }
    public Rover buildRover(Coordinate startingPoint, Direction directionFacing ){
        Rover rover = new Rover();
        rover.setDirectionFacing(directionFacing);
        rover.setCoordinate(startingPoint);
        rover.setObject(Object.ROVER);
        return rover;
    }

    public Obstacle buildObstacle(Coordinate obstacleCoordinate){
        Obstacle obstacle = new Obstacle();
        obstacle.setCoordinate(obstacleCoordinate);
        obstacle.setObject(Object.OBSTACLE);
        return obstacle;
    }

    public boolean setRoverPosition(Rover rover, Board board){ // revisar este codigo repetido
        if(board.getBoard()[rover.getCoordinate().getRow()][rover.getCoordinate().getColumn()].getObject() !=Object.OBSTACLE){
            board.getBoard()[rover.getCoordinate().getRow()][rover.getCoordinate().getColumn()] = rover; 
            return true;
        } else{
            return false;
        }
   
    }
    public void setObstaclePosition(Obstacle obstacle, Board board){ // revisar este codigo repetido
        if(board.getBoard()[obstacle.getCoordinate().getRow()][obstacle.getCoordinate().getColumn()].getObject() != Object.ROVER){
            board.getBoard()[obstacle.getCoordinate().getRow()][obstacle.getCoordinate().getColumn()] = obstacle; 
        }      
    }

    public void setEmptyCellPosition(Coordinate coordinate, Board board){
        EmptyCell emptyCell = new EmptyCell();
        emptyCell.setCoordinate(coordinate);
        emptyCell.setObject(Object.EMPTY);
        board.getBoard()[coordinate.getRow()][coordinate.getColumn()] = emptyCell; 
    }

    public void moveRove(ArrayList<Move> movements, Rover rover, Board board ){

          for(int i = 0; i < movements.size(); i++){

            switch(movements.get(i)) {
                case LEFT:
                    this.turnLeft(rover, board);
                  break;
                case RIGHT:
                    this.turnRight(rover,board);
                  break;
                case BACKWARD:
                    this.moveBackward(rover,board);
                  break;
                case FORWARD:
                    this.moveForward(rover,board);
                  break;
              }
          
            
        }
        printBoard(board);

    }
    public void turnLeft(Rover rover, Board board){
        Coordinate coordinate = new Coordinate();
        switch(rover.getDirectionFacing()) {
            case N:
                coordinate = this.subCoordinateRow(rover.getCoordinate());
              break;
            case S:
                coordinate = this.addCoordinateRow(rover.getCoordinate());
              break;
            case E:
                coordinate = this.addCoordinateColumn(rover.getCoordinate());
              break;
            case W:
                coordinate = this.subCoordinateColumn(rover.getCoordinate());
              break;
          }  
          rover = this.updateRoverCoordinate(rover, coordinate);
          boolean s = this.setRoverPosition(rover, board);
          if(s){

            this.setEmptyCellPosition(coordinate, board);  
          }
    }
    public void turnRight(Rover rover, Board board){
        Coordinate coordinate = new Coordinate();
        switch(rover.getDirectionFacing()) {
            case N:
                coordinate = this.addCoordinateRow(rover.getCoordinate());
              break;
            case S:
                coordinate = this.subCoordinateRow(rover.getCoordinate());
              break;
            case E:
                coordinate = this.subCoordinateColumn(rover.getCoordinate());
              break;
            case W:
                coordinate = this.addCoordinateColumn(rover.getCoordinate());
              break;
          }  
          rover = this.updateRoverCoordinate(rover, coordinate);
          boolean s =  this.setRoverPosition(rover, board); 
          if(s){

            this.setEmptyCellPosition(coordinate, board);  
          }  
    }
    public void moveForward(Rover rover, Board board){
        Coordinate coordinate = new Coordinate();
        switch(rover.getDirectionFacing()) {
            case N:
                coordinate = this.addCoordinateColumn(rover.getCoordinate());
              break;
            case S:
                coordinate = this.subCoordinateColumn(rover.getCoordinate());
              break;
            case E:
                coordinate = this.addCoordinateRow(rover.getCoordinate());
              break;
            case W:
                coordinate = this.subCoordinateRow(rover.getCoordinate());
              break;
          }  
          rover = this.updateRoverCoordinate(rover, coordinate);
          boolean s = this.setRoverPosition(rover, board);  
          if(s){

            this.setEmptyCellPosition(coordinate, board);  
          }
    }
    public void moveBackward(Rover rover, Board board){
        Coordinate coordinate = new Coordinate();
        switch(rover.getDirectionFacing()) {
            case N:
                coordinate = this.subCoordinateColumn(rover.getCoordinate());                
              break;
            case S:
                coordinate = this.addCoordinateColumn(rover.getCoordinate());              
              break;
            case E:
                coordinate = this.subCoordinateRow(rover.getCoordinate());            
              break;
            case W:
                coordinate = this.addCoordinateRow(rover.getCoordinate());          
              break;
          }  
          rover = this.updateRoverCoordinate(rover, coordinate);
          boolean s =  this.setRoverPosition(rover, board);
          if(s){

            this.setEmptyCellPosition(coordinate, board);  
          }
    }

    public Rover updateRoverCoordinate(Rover rover, Coordinate newCoordinate){
        rover.setCoordinate(newCoordinate);
        return rover;
    }

    public Coordinate addCoordinateRow(Coordinate coordinate){
        int newRowPosition = coordinate.getRow() + 1;
        coordinate.setRow(newRowPosition);
        return coordinate;
    }

    public Coordinate addCoordinateColumn(Coordinate coordinate){
        int newColumnPosition = coordinate.getColumn() + 1;
        coordinate.setColumn(newColumnPosition);
        return coordinate;
    }

    public Coordinate subCoordinateRow(Coordinate coordinate){
        int newRowPosition = coordinate.getRow() - 1;
        coordinate.setRow(newRowPosition);
        return coordinate;
    }

    public Coordinate subCoordinateColumn(Coordinate coordinate){
        int newColumnPosition = coordinate.getColumn() - 1;
        coordinate.setColumn(newColumnPosition);
        return coordinate;
    }
    public void printBoard(Board board){
    StringBuilder topLines = new StringBuilder();
    StringBuilder midLines = new StringBuilder();
    StringBuilder columnNumber = new StringBuilder();
    for (int x = 0; x < board.getBoard().length; ++x) {
        if(x==0){
            topLines.append(" +---------");
        }else{
            topLines.append("+---------");
        }
        midLines.append(" |        ");
        columnNumber.append("     "+x+"    ");
    }
    topLines.append("+");
    midLines.append(" |");

    for (int y = 0; y < board.getBoard().length; ++y) {
        if(y==0){
            System.out.println(columnNumber);
        }
        System.out.println(topLines);
        System.out.println(midLines);
        for (int x = 0; x < board.getBoard().length; ++x) {
            if(x==0){
                System.out.print(+y+"|");
            }else{
                System.out.print(" |");
            }
                StringBuilder output = new StringBuilder(board.getBoard()[x][y].getObject().toString());
                while (output.length() < 8) {
                    output.append(" ");
                    if (output.length() < 8) {
                        output.insert(0, " ");
                    }
                }
                System.out.print(output);
            
        }
        System.out.println(" |");
        System.out.println(midLines);
    }
    System.out.println(topLines);  
    }

    public Board intializeBoard(Board board){
        EmptyCell cell = new EmptyCell(); // cambiar
        cell.setId(1);
        cell.setObject(Object.EMPTY);
        for(int x = 0 ; x < board.getBoard().length ; x++){
            for(int y = 0 ; y < board.getBoard().length ; y++){
                Coordinate coordinate = new Coordinate ();
                coordinate.setColumn(y);
                coordinate.setColumn(x);
                cell.setCoordinate(coordinate);
                board.getBoard()[x][y] = cell;
            }
        }
        return board;

    }


    
}
