package merkle.test.marsroverkata.service.impl;

import merkle.test.marsroverkata.service.BoardService;
import merkle.test.marsroverkata.service.CellService;
import merkle.test.marsroverkata.model.Rover;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import merkle.test.marsroverkata.enums.Direction;
import merkle.test.marsroverkata.enums.Object;
import merkle.test.marsroverkata.model.Board;
import merkle.test.marsroverkata.model.Cell;
import merkle.test.marsroverkata.model.Coordinates;
import merkle.test.marsroverkata.model.EmptyCell;
import merkle.test.marsroverkata.model.Obstacle;

@Service
public class CellServiceImpl implements CellService{

    @Autowired
    private BoardService boardService;

    public Rover buildRover(Coordinates startingPoint, Direction directionFacing ){
        Rover rover = new Rover();
        rover.setDirectionFacing(directionFacing);
        rover.setCoordinates(startingPoint);
        rover.setObject(Object.ROVER);
        return rover;
    }
    public Obstacle buildObstacle(Coordinates obstacleCoordinates){
        Obstacle obstacle = new Obstacle();
        obstacle.setCoordinates(obstacleCoordinates);
        obstacle.setObject(Object.OBSTACLE);
        return obstacle;
    }
    @Override
    public EmptyCell buildEmptyCell(Coordinates emptyCellCoordinates) {
        EmptyCell emptyCell = new EmptyCell();
        emptyCell.setCoordinates(emptyCellCoordinates);
        emptyCell.setObject(Object.EMPTY);
        return emptyCell;
    }

    public void setRoverInBoard(Rover rover, Board board){
        Coordinates roverCoordinates = rover.getCoordinates();
        Cell cellBoard = this.boardService.getCellBoard(roverCoordinates, board);
        if(cellBoard.getObject() == Object.EMPTY){
            this.boardService.setCellBoard(board, rover);
        } 
    }

    public void setObstacleInBoard(Obstacle obstacle, Board board){ 
        Coordinates obstacleCoordinates = obstacle.getCoordinates();
        Cell cellBoard = this.boardService.getCellBoard(obstacleCoordinates, board);
        if(cellBoard.getObject() == Object.EMPTY){
            System.out.print("Set obstacle in position: ["+obstacleCoordinates.getRow()+","+obstacleCoordinates.getColumn()+"]\n");
            this.boardService.setCellBoard(board, obstacle);
        }     
    }

    public void setEmptyCellInBoard(Coordinates coordinates, Board board){
        EmptyCell emptyCell = this.buildEmptyCell(coordinates);
        this.boardService.setCellBoard(board, emptyCell);
    }

    public Cell updateCellCordinates(Cell cell, Coordinates newCoordinates){
        cell.setCoordinates(newCoordinates);
        return cell;
    }
    
}
