package merkle.test.marsroverkata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import merkle.test.marsroverkata.service.WorldService;

import merkle.test.marsroverkata.enums.Object;
import merkle.test.marsroverkata.model.World;
import merkle.test.marsroverkata.model.Coordinates;
import merkle.test.marsroverkata.service.CoordinatesService;



@Service
public class CoordinatesServiceImpl implements CoordinatesService{

    @Autowired
    private WorldService worldService;

    /**
     * métodos para sumar o restar coordenadas
     */
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

    /**
     * devuelve true si es posible que el rover se mueva hacia 
     * la posible coordenada ya que no habrá nada
     */
    public boolean movementIsPossible(World world, Coordinates possibleCoordinate){
        final int row = possibleCoordinate.getRow();
        final int column = possibleCoordinate.getColumn();
        worldService.getPlanetWorld(possibleCoordinate, world);
        Object object = world.getWorld()[row][column].getObject();
        return  object == Object.EMPTY;
      }

      /**
       * si un rover está al limite de la cuadricula y decide moverse
       * empieza al inicio de esta
       */
    public Coordinates turnPlanetIfNecessary(Coordinates coordinates, World world){
        final int row = coordinates.getRow();
        final int column = coordinates.getColumn();
        final int lengthWorld = world.getWorld().length - 1;
        if(row > lengthWorld){
            coordinates.setRow(0);
            System.out.println("Turn in direction +X");
        }else if (row<0){
            coordinates.setRow(lengthWorld);
            System.out.println("Turn the in direction -X");
        }else if (column > lengthWorld){
            coordinates.setColumn(0);
            System.out.println("Turn the planet in direction +Y");
        }else if (column < 0){
            coordinates.setColumn(lengthWorld);
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
