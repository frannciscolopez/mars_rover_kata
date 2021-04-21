package merkle.test.marsroverkata.utils;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import merkle.test.marsroverkata.model.World;
import merkle.test.marsroverkata.model.Coordinates;
import merkle.test.marsroverkata.service.CoordinatesService;

@Service
public class Utils {

    @Autowired
    private CoordinatesService coordinatesService;

    public void printWorld(World world){
        StringBuilder topLines = new StringBuilder();
        StringBuilder midLines = new StringBuilder();
        StringBuilder columnNumber = new StringBuilder();
        for (int x = 0; x < world.getWorld().length; ++x) {
            if(x==0){
                topLines.append(" +---------");
            }else{
                topLines.append("+---------");
            }
            midLines.append(" |        ");
            columnNumber.append("     "+x+"    ");
        }
        topLines.append("+");
        midLines.append(" |");
    
        for (int y = 0; y < world.getWorld().length; ++y) {
            if(y==0){
                System.out.println(columnNumber);
            }
            System.out.println(topLines);
            System.out.println(midLines);
            for (int x = 0; x < world.getWorld().length; ++x) {
                if(x==0){
                    System.out.print(+y+"|");
                }else{
                    System.out.print(" |");
                }
                    StringBuilder output = new StringBuilder(world.getWorld()[x][y].getObject().toString());
                    while (output.length() < 8) {
                        output.append(" ");
                        if (output.length() < 8) {
                            output.insert(0, " ");
                        }
                    }
                    System.out.print(output);
                
            }
            System.out.println(" |");
            System.out.println(midLines);
        }
        System.out.println(topLines);  
        }

        public Coordinates getRandomObstacleCoordinates(World world){
            Random rand = new Random();
            Coordinates randomCoordinates = new Coordinates();
            int lengthWorld = world.getWorld().length;
            int row  = rand.nextInt(lengthWorld-1);
            int column = rand.nextInt(lengthWorld-1);
            randomCoordinates = coordinatesService.buildCoordinates(row, column);
            return randomCoordinates ;
        }

}
