package merkle.test.marsroverkata.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import merkle.test.marsroverkata.enums.Direction;
import merkle.test.marsroverkata.model.Coordinates;
import merkle.test.marsroverkata.service.MainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/rover-management")
public class GameController {

    @Autowired
    private MainService gameService;
    
    /**
     * endpoint de dónde se iniciará todo el funcionamiento del sistema
     * @param startingPoint
     * @param directionFacing
     * @param worldSize
     */
    @GetMapping(value = "/start/{directionFacing}/{worldSize}")
    public void startGame(@RequestBody Coordinates startingPoint, @PathVariable(value = "directionFacing") Direction directionFacing,
    @PathVariable(value = "worldSize") int worldSize){
        this.gameService.createWorld(startingPoint, directionFacing, worldSize);
    }
    
}
