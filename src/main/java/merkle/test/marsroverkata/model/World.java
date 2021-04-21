package merkle.test.marsroverkata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class World {
    private Planet [][] world;

    public World (int worldSize){
       this.world = new Planet[worldSize][worldSize];
        
    }
}
