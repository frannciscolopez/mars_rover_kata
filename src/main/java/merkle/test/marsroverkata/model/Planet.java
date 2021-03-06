package merkle.test.marsroverkata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import merkle.test.marsroverkata.enums.Object;

@RequiredArgsConstructor
@Data
public abstract class Planet{
    private int id;
    private Object object;
    private Coordinates coordinates;
}
