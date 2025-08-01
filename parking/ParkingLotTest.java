package parking;

import vehicle.Car;
import vehicle.Size;
import parking.facility.Gate;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ParkingLotTest{
    @Test
    public void testConstructorWithInvalidValues(){
        try{
            ParkingLot pl = new ParkingLot(0,0);
            fail();
        }
        catch(IllegalArgumentException e) {}    
    }
    @Test
    public void testTextualRepresentation(){
        ParkingLot pl = new ParkingLot(5,5);
        Gate g = new Gate(pl);


        Car c8 = new Car("g", Size.SMALL, 4);
        Car c9 = new Car("h", Size.SMALL, 4);
        Car c10 = new Car("i", Size.SMALL, 4);
        Car c11 = new Car("j", Size.SMALL, 4);

        Car c12 = new Car("k", Size.SMALL, 3);
        Car c13 = new Car("l", Size.SMALL, 3);
        Car c14 = new Car("m", Size.SMALL, 3);
        Car c15 = new Car("n", Size.LARGE, 3);

        Car c16 = new Car("o", Size.SMALL, 3);
        Car c17 = new Car("p", Size.SMALL, 3);
        Car c18 = new Car("r", Size.SMALL, 3);
        Car c19 = new Car("s", Size.LARGE, 3);

        Car c1 = new Car("a", Size.LARGE, 1);
        Car c2 = new Car("b", Size.LARGE, 1);

        Car c5 = new Car("g", Size.SMALL, 0);
        Car c3 = new Car("c", Size.SMALL, 0);
        Car c4 = new Car("d", Size.SMALL, 0);

        
        g.registerCar(c8);
        g.registerCar(c9);
        g.registerCar(c10);
        g.registerCar(c11);
        
        g.registerCar(c12);
        g.registerCar(c13);
        g.registerCar(c14);
        g.registerCar(c15);

        g.registerCar(c16);
        g.registerCar(c17);
        g.registerCar(c18);
        g.registerCar(c19);

        g.registerCar(c1);
        g.registerCar(c2);

        g.registerCar(c5);
        g.registerCar(c3);
        g.registerCar(c4);

        g.deRegisterCar("ticket - g");
        g.deRegisterCar("ticket - h");
        g.deRegisterCar("ticket - i");

        g.deRegisterCar("ticket - k");

        g.deRegisterCar("ticket - p");

        g.deRegisterCar("ticket - g");    

        String expected = "X X X S X \nX S S L L \nS X S L L \nL L L L X \nX S S X X \n";
        assertEquals(expected, pl.toString());
    }
}