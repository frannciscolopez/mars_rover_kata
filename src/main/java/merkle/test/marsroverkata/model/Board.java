package merkle.test.marsroverkata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Board {
    private Cell [][] board;

    public Board (int boardSize){
       this.board = new Cell[boardSize][boardSize];
        
    }
}
