package merkle.test.marsroverkata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import merkle.test.marsroverkata.model.Board;
import merkle.test.marsroverkata.model.Cell;
import merkle.test.marsroverkata.model.Coordinates;
import merkle.test.marsroverkata.model.EmptyCell;
import merkle.test.marsroverkata.enums.Object;
import merkle.test.marsroverkata.service.BoardService;
import merkle.test.marsroverkata.service.CellService;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private CellService cellService;


    @Override
    public Board createBoard(int boardSize){
        Board board = new Board(boardSize);
        return board;
    }

    @Override
    public Cell getCellBoard(Coordinates cellCoordinate, Board board) {
        int row = cellCoordinate.getRow();
        int column = cellCoordinate.getColumn();
        Cell cellBoard = board.getBoard()[row][column];
        return cellBoard;
    }

    @Override
    public void setCellBoard(Board board, Cell object) {
        int row = object.getCoordinates().getRow();
        int column = object.getCoordinates().getColumn();
        board.getBoard()[row][column] = object;
    }  
    
    public Board intializeBoard(Board board){
        EmptyCell emptyCell = new EmptyCell();
        Coordinates emptyCellCoordinates = new Coordinates();

        for(int x = 0 ; x < board.getBoard().length ; x++){
            for(int y = 0 ; y < board.getBoard().length ; y++){
                emptyCellCoordinates.setRow(x);
                emptyCellCoordinates.setColumn(y);
                emptyCell = this.cellService.buildEmptyCell(emptyCellCoordinates);
                this.setCellBoard(board, emptyCell);
            }
        }
        return board;
    } 
    
    
}
