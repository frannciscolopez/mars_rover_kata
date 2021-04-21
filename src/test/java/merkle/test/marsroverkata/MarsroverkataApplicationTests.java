package merkle.test.marsroverkata;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import merkle.test.marsroverkata.enums.Direction;
import merkle.test.marsroverkata.enums.Object;
import merkle.test.marsroverkata.model.World;
import merkle.test.marsroverkata.model.Planet;
import merkle.test.marsroverkata.model.Coordinates;
import merkle.test.marsroverkata.model.EmptyPlanet;
import merkle.test.marsroverkata.model.Obstacle;
import merkle.test.marsroverkata.model.Rover;
import merkle.test.marsroverkata.service.PlanetService;
import merkle.test.marsroverkata.service.CoordinatesService;
import merkle.test.marsroverkata.service.impl.WorldServiceImpl;
import merkle.test.marsroverkata.service.impl.PlanetServiceImpl;
import merkle.test.marsroverkata.service.impl.CoordinatesServiceImpl;
import merkle.test.marsroverkata.service.impl.MainServiceImpl;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class MarsroverkataApplicationTests {

	@InjectMocks
    private CoordinatesServiceImpl mockCoordinatesServiceImpl;

	@InjectMocks
    private WorldServiceImpl mockWorldServiceImpl;

	@Spy
    private PlanetServiceImpl mockPlanetServiceImpl ;

	@InjectMocks
    private MainServiceImpl mainServiceImpl;


	@Test
	public void builCoordinatesTest() {
		Coordinates mockCoordinates = this.mockCoordinatesServiceImpl.buildCoordinates(1,2);
		assertEquals(1, mockCoordinates.getRow());
		assertEquals(2, mockCoordinates.getColumn());
	}

	@Test
	public void addCoordinatesColumnTest() {
		Coordinates utilCoordinates = this.mockCoordinatesServiceImpl.buildCoordinates(1,2);
		Coordinates mockCoordinates = this.mockCoordinatesServiceImpl.addCoordinatesColumn(utilCoordinates);
		assertEquals(1, mockCoordinates.getRow());
		assertEquals(3, mockCoordinates.getColumn());
	}

	@Test
	public void addCoordinatesRowTest() {
		Coordinates utilCoordinates = this.mockCoordinatesServiceImpl.buildCoordinates(1,2);
		Coordinates mockCoordinates = this.mockCoordinatesServiceImpl.addCoordinatesRow(utilCoordinates);
		assertEquals(2, mockCoordinates.getRow());
		assertEquals(2, mockCoordinates.getColumn());
	}

	@Test
	public void subCoordinatesColumnTest() {
		Coordinates utilCoordinates = this.mockCoordinatesServiceImpl.buildCoordinates(1,2);
		Coordinates mockCoordinates = this.mockCoordinatesServiceImpl.subCoordinatesColumn(utilCoordinates);
		assertEquals(1, mockCoordinates.getRow());
		assertEquals(1, mockCoordinates.getColumn());
	}

	@Test
	public void subCoordinatesRowTest() {
		Coordinates utilCoordinates = this.mockCoordinatesServiceImpl.buildCoordinates(1,2);
		Coordinates mockCoordinates = this.mockCoordinatesServiceImpl.subCoordinatesRow(utilCoordinates);
		assertEquals(0, mockCoordinates.getRow());
		assertEquals(2, mockCoordinates.getColumn());
	}

	@Test
	public void createWorldTest() {
		World world = this.mockWorldServiceImpl.createWorld(3);
		int worldLength = world.getWorld().length;
		assertEquals(3, worldLength);
	}

	@Test
	public void getplanetWorldTest() {
		Coordinates utilCoordinates = this.mockCoordinatesServiceImpl.buildCoordinates(1,2);
		World world = this.mockWorldServiceImpl.createWorld(3);
		Planet emptyPlanet = this.mockPlanetServiceImpl.buildEmptyPlanet(utilCoordinates);
		this.mockWorldServiceImpl.setPlanetWorld(world, emptyPlanet);
		Planet planet = this.mockWorldServiceImpl.getPlanetWorld(utilCoordinates, world);
		assertEquals(1, planet.getCoordinates().getRow());
		assertEquals(2, planet.getCoordinates().getColumn());
	}
	@Test
	public void buildRoverTest() {
		Coordinates utilCoordinates = this.mockCoordinatesServiceImpl.buildCoordinates(1,2);
		Direction directionFacing = Direction.N;
		Rover rover = this.mockPlanetServiceImpl.buildRover(utilCoordinates, directionFacing);
		assertEquals(1, rover.getCoordinates().getRow());
		assertEquals(2, rover.getCoordinates().getColumn());
		assertEquals(directionFacing, rover.getDirectionFacing());
		assertEquals(Object.ROVER, rover.getObject());
	}
	@Test
	public void buildObstacleTest() {
		Coordinates utilCoordinates = this.mockCoordinatesServiceImpl.buildCoordinates(1,2);
		Obstacle obstacle = this.mockPlanetServiceImpl.buildObstacle(utilCoordinates);
		assertEquals(1, obstacle.getCoordinates().getRow());
		assertEquals(2, obstacle.getCoordinates().getColumn());
		assertEquals(Object.OBSTACLE, obstacle.getObject());
	}
	@Test
	public void buildEmptyPlanetTest() {
		Coordinates utilCoordinates = this.mockCoordinatesServiceImpl.buildCoordinates(1,2);
		EmptyPlanet emptyPlanet = this.mockPlanetServiceImpl.buildEmptyPlanet(utilCoordinates);
		assertEquals(1, emptyPlanet.getCoordinates().getRow());
		assertEquals(2, emptyPlanet.getCoordinates().getColumn());
		assertEquals(Object.EMPTY, emptyPlanet.getObject());
	}
	@Test
	public void updatePlanetCordinatesTest() {
		Coordinates utilCoordinates = this.mockCoordinatesServiceImpl.buildCoordinates(1,2);
		Coordinates utilOldCoordinates = this.mockCoordinatesServiceImpl.buildCoordinates(1,2);
		Planet planet = this.mockPlanetServiceImpl.buildEmptyPlanet(utilOldCoordinates);
		this.mockPlanetServiceImpl.updatePlanetCordinates(planet,utilCoordinates);
		assertEquals(1, planet.getCoordinates().getRow());
		assertEquals(2, planet.getCoordinates().getColumn());
	}

	@Test
	public void turnPlanetTest() {
		Coordinates utilCoordinates;
		utilCoordinates = this.mockCoordinatesServiceImpl.buildCoordinates(3,2);
		World world = this.mockWorldServiceImpl.createWorld(3);
		Coordinates turnCoordinates;
		turnCoordinates = this.mockCoordinatesServiceImpl.turnPlanetIfNecessary(utilCoordinates, world);
		assertEquals(0, turnCoordinates.getRow());

		utilCoordinates = this.mockCoordinatesServiceImpl.buildCoordinates(-1,2);
		turnCoordinates = this.mockCoordinatesServiceImpl.turnPlanetIfNecessary(utilCoordinates, world);
		assertEquals(2, turnCoordinates.getRow());

		utilCoordinates = this.mockCoordinatesServiceImpl.buildCoordinates(0,3);
		turnCoordinates = this.mockCoordinatesServiceImpl.turnPlanetIfNecessary(utilCoordinates, world);
		assertEquals(0, turnCoordinates.getColumn());


		utilCoordinates = this.mockCoordinatesServiceImpl.buildCoordinates(0,-1);
		turnCoordinates = this.mockCoordinatesServiceImpl.turnPlanetIfNecessary(utilCoordinates, world);
		assertEquals(2, turnCoordinates.getColumn());
	}


	@Test
	public void buildWorldTest() {
		World world = this.mockWorldServiceImpl.createWorld(3);
		this.mockWorldServiceImpl.initializeWorld(world);
	    assertEquals(Object.EMPTY, world.getWorld()[2][2].getObject());
            
        
	}	

}
