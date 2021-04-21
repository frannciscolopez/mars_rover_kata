package merkle.test.marsroverkata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import merkle.test.marsroverkata.model.World;
import merkle.test.marsroverkata.model.Planet;
import merkle.test.marsroverkata.model.Coordinates;
import merkle.test.marsroverkata.model.EmptyPlanet;
import merkle.test.marsroverkata.enums.Object;
import merkle.test.marsroverkata.service.WorldService;
import merkle.test.marsroverkata.service.PlanetService;

@Service
public class WorldServiceImpl implements WorldService{

    @Autowired
    private PlanetService planetService;

    /**
     * inicia el array bidimensional sobre el que montamos el mundo
     */

    @Override
    public World createWorld(int worldSize){
        World world = new World(worldSize);
        return world;
    }

    /**
     * obtenemos un planeta a partir de unas coordenadas
     */
    @Override
    public Planet getPlanetWorld(Coordinates planetCoordinate, World world) {
        int row = planetCoordinate.getRow();
        int column = planetCoordinate.getColumn();
        Planet planetWorld = world.getWorld()[row][column];
        return planetWorld;
    }

    /**
     * colocamos un planeta en el mundo
     */
    @Override
    public void setPlanetWorld(World world, Planet object) {
        int row = object.getCoordinates().getRow();
        int column = object.getCoordinates().getColumn();
        world.getWorld()[row][column] = object;
    }  
   
    /**
     * creamos un mundo vacío
     */
    public World initializeWorld(World world){
        EmptyPlanet emptyPlanet = new EmptyPlanet();
        Coordinates emptyPlanetCoordinates = new Coordinates();

        for(int x = 0 ; x < world.getWorld().length ; x++){
            for(int y = 0 ; y < world.getWorld().length ; y++){
                emptyPlanetCoordinates.setRow(x);
                emptyPlanetCoordinates.setColumn(y);
                emptyPlanet = this.planetService.buildEmptyPlanet(emptyPlanetCoordinates);
                this.setPlanetWorld(world, emptyPlanet);
            }
        }
        return world;
    } 
    
    
}
