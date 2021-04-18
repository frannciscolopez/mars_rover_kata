package merkle.test.marsroverkata.utils;

import org.springframework.stereotype.Service;

import merkle.test.marsroverkata.model.Board;

@Service
public class Utils {


    public Board buildBoard(int boardSize){
        Board board = new Board(boardSize);
        return board;
    }
    
}
