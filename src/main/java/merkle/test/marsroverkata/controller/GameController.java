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
@RequestMapping("/game-controls")
public class GameController {

    @Autowired
    private MainService gameService;
    
    @GetMapping(value = "/start-game/{directionFacing}/{boardSize}")
    public void startGame(@RequestBody Coordinates startingPoint, @PathVariable(value = "directionFacing") Direction directionFacing,
    @PathVariable(value = "boardSize") int boardSize){
        this.gameService.createWorld(startingPoint, directionFacing, boardSize);
    }
    
}
