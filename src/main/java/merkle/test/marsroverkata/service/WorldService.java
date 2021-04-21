package merkle.test.marsroverkata.service;

import org.springframework.stereotype.Service;

import merkle.test.marsroverkata.model.World;
import merkle.test.marsroverkata.model.Planet;
import merkle.test.marsroverkata.model.Coordinates;
@Service
public interface WorldService {
  World createWorld(int worldSize);  
  Planet getPlanetWorld(Coordinates planetCoordinate, World world);   
  void setPlanetWorld(World world, Planet object);   
  World initializeWorld(World world);
}
