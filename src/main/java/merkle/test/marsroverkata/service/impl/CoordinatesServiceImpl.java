package merkle.test.marsroverkata.service.impl;

import org.springframework.stereotype.Service;

import merkle.test.marsroverkata.enums.Object;
import merkle.test.marsroverkata.model.Board;
import merkle.test.marsroverkata.model.Coordinates;
import merkle.test.marsroverkata.service.CoordinatesService;

@Service
public class CoordinatesServiceImpl implements CoordinatesService{

    public Coordinates addCoordinatesRow(Coordinates coordinates){
        int newRowPosition = coordinates.getRow() + 1;
        Coordinates newCoordinate = this.buildCoordinates(newRowPosition, coordinates.getColumn());
        return newCoordinate;
    }

    public Coordinates addCoordinatesColumn(Coordinates coordinates){
        int newColumnPosition = coordinates.getColumn() + 1;
        Coordinates newCoordinates = this.buildCoordinates(coordinates.getRow(), newColumnPosition);
        return newCoordinates;
    }

    public Coordinates subCoordinatesRow(Coordinates coordinates){
        int newRowPosition = coordinates.getRow() - 1;
        Coordinates newCoordinates = this.buildCoordinates(newRowPosition, coordinates.getColumn());
        return newCoordinates;
    }

    public Coordinates subCoordinatesColumn(Coordinates coordinates){
        int newColumnPosition = coordinates.getColumn() - 1;
        Coordinates newCoordinates = this.buildCoordinates(coordinates.getRow(), newColumnPosition);
        return newCoordinates;
    }

    public boolean movementIsPossible(Board board, Coordinates possibleCoordinate){
        return board.getBoard()[possibleCoordinate.getRow()][possibleCoordinate.getColumn()].getObject() == Object.EMPTY;
      }

    public Coordinates turnPlanetIfNecessary(Coordinates coordinates, Board board){
        final int row = coordinates.getRow();
        final int column = coordinates.getColumn();
        final int lengthBoard = board.getBoard().length - 1;
        if(row == lengthBoard){
            coordinates.setRow(0);
            System.out.println("Turn in direction +X");
        }else if (row<0){
            coordinates.setRow(lengthBoard);
            System.out.println("Turn the in direction -X");
        }else if (column > lengthBoard){
            coordinates.setColumn(0);
            System.out.println("Turn the planet in direction +Y");
        }else if (column < 0){
            coordinates.setColumn(lengthBoard);
            System.out.println("Turn in direction -Y");
        }
        return coordinates;
    }  
  
    public Coordinates buildCoordinates(int row, int column){
        Coordinates coordinates = new Coordinates();
        coordinates.setRow(row);
        coordinates.setColumn(column);
        return coordinates;
    }
}
