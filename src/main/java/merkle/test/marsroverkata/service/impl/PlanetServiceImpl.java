package merkle.test.marsroverkata.service.impl;

import merkle.test.marsroverkata.service.WorldService;
import merkle.test.marsroverkata.service.PlanetService;
import merkle.test.marsroverkata.model.Rover;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import merkle.test.marsroverkata.enums.Direction;
import merkle.test.marsroverkata.enums.Object;
import merkle.test.marsroverkata.model.World;
import merkle.test.marsroverkata.model.Planet;
import merkle.test.marsroverkata.model.Coordinates;
import merkle.test.marsroverkata.model.EmptyPlanet;
import merkle.test.marsroverkata.model.Obstacle;

@Service
public class PlanetServiceImpl implements PlanetService{

    @Autowired
    private WorldService worldService;

    /**
     * construcción de rover a partir de una dirección de inicio y una orientación
     */
    public Rover buildRover(Coordinates startingPoint, Direction directionFacing ){
        Rover rover = new Rover();
        rover.setDirectionFacing(directionFacing);
        rover.setCoordinates(startingPoint);
        rover.setObject(Object.ROVER);
        return rover;
    }

    /**
     * construimos un obstáculo a partir de unas coordenadas
     */
    public Obstacle buildObstacle(Coordinates obstacleCoordinates){
        Obstacle obstacle = new Obstacle();
        obstacle.setCoordinates(obstacleCoordinates);
        obstacle.setObject(Object.OBSTACLE);
        return obstacle;
    }
     /**
     * construimos un planeta vacío a partir de unas coordenadas
     */
    @Override
    public EmptyPlanet buildEmptyPlanet(Coordinates emptyPlanetCoordinates) {
        EmptyPlanet emptyPlanet = new EmptyPlanet();
        emptyPlanet.setCoordinates(emptyPlanetCoordinates);
        emptyPlanet.setObject(Object.EMPTY);
        return emptyPlanet;
    }

    /**
     * ponemos el diferente tipo de objeto en el planeta 
     */
    public void setRoverInWorld(Rover rover, World world){
        Coordinates roverCoordinates = rover.getCoordinates();
        Planet planetWorld = this.worldService.getPlanetWorld(roverCoordinates, world);
        if(planetWorld.getObject() == Object.EMPTY){
            this.worldService.setPlanetWorld(world, rover);
        } 
    }

    public void setObstacleInWorld(Obstacle obstacle, World world){ 
        Coordinates obstacleCoordinates = obstacle.getCoordinates();
        Planet planetWorld = this.worldService.getPlanetWorld(obstacleCoordinates, world);
        if(planetWorld.getObject() == Object.EMPTY){
            System.out.print("Set obstacle in position: ["+obstacleCoordinates.getRow()+","+obstacleCoordinates.getColumn()+"]\n");
            this.worldService.setPlanetWorld(world, obstacle);
        }     
    }

    public void setEmptyPlanetInWorld(Coordinates coordinates, World world){
        EmptyPlanet emptyPlanet = this.buildEmptyPlanet(coordinates);
        this.worldService.setPlanetWorld(world, emptyPlanet);
    }

    public Planet updatePlanetCordinates(Planet planet, Coordinates newCoordinates){
        planet.setCoordinates(newCoordinates);
        return planet;
    }
    
}
