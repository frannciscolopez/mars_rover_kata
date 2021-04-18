package merkle.test.marsroverkata.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import merkle.test.marsroverkata.enums.Direction;
import merkle.test.marsroverkata.enums.Move;
import merkle.test.marsroverkata.model.Coordinate;
import merkle.test.marsroverkata.service.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@RestController
@RequestMapping("/game-controls")
public class GameController {

    @Autowired
    private GameService gameService;

    ArrayList<Move> movements = new ArrayList<Move>();
    

    @GetMapping(value = "/start-game")
    public void startGame(@RequestBody Coordinate startingPoint, Direction directionFacing, int boardSize){
        this.gameService.startGame(startingPoint, directionFacing, boardSize);
    }

    @GetMapping(value = "/process-movements")
    public void startGame(){
   
        this.gameService.processMovements(movements);
    }

    @GetMapping(value ="/prueba")
    public void prueba(){
        Coordinate s = new Coordinate();
        s.setColumn(2);
        s.setRow(1);
        this.gameService.startGame(s, Direction.N, 4);
    }



    
}
