package merkle.test.marsroverkata.model;

import merkle.test.marsroverkata.enums.Direction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Rover extends Planet{
    private String name;
    private Direction directionFacing;
}
