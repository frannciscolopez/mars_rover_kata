package merkle.test.marsroverkata.service;

import org.springframework.stereotype.Service;

import merkle.test.marsroverkata.model.Board;
import merkle.test.marsroverkata.model.Cell;
import merkle.test.marsroverkata.model.Coordinates;
@Service
public interface BoardService {
  Board createBoard(int boardSize);  
  Cell getCellBoard(Coordinates cellCoordinate, Board board);   
  void setCellBoard(Board board, Cell object);   
  Board intializeBoard(Board board);
}
