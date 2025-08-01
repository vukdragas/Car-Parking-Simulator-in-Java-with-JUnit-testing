package parking.facility;

import parking.ParkingLot;
import vehicle.Size;
import vehicle.Car;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GateTest{
    @Test
    public void testFindAnyAvailableSpaceForCar(){
        ParkingLot pl = new ParkingLot(2,2);
        Gate g = new Gate(pl);
        
        Car c1 = new Car("a", Size.SMALL,0);
        Car c2 = new Car("b", Size.SMALL,0);
        Car c3 = new Car("c", Size.SMALL,1);
        Car c4 = new Car("d", Size.SMALL,1);
        Car c5 = new Car("e", Size.SMALL,1);

        g.registerCar(c1);
        g.registerCar(c2);
        g.registerCar(c3);
        g.registerCar(c4);

        assertNull(g.findAnyAvailableSpaceForCar(c5));
    }

    @ParameterizedTest
    @CsvSource({"'a', 'SMALL', 0","'b', 'LARGE', 1","'c', 'SMALL', 2","'d', 'LARGE', 0"})
    public void testFindPreferredAvailableSpaceForCar(String plate, Size size, int preferredFloor){
        ParkingLot pl = new ParkingLot(2,2);
        Gate g = new Gate(pl);
        Car car = new Car(plate, size, preferredFloor);
        
        assertNotNull(g.findPreferredAvailableSpaceForCar(car));
    }

    @ParameterizedTest
    @CsvSource({"'a', 'SMALL', 0","'b', 'LARGE', 1","'c', 'SMALL', 2","'d', 'LARGE', 0"})
    public void testRegisterCar(String plate, Size size, int preferredFloor){
        ParkingLot pl = new ParkingLot(2,2);
        Gate g = new Gate(pl);
        Car car = new Car(plate, size, preferredFloor);
        
        assertTrue(g.registerCar(car));
    }

    @ParameterizedTest
    @CsvSource({"'a', 'SMALL', 0","'b', 'LARGE', 1","'c', 'SMALL', 2","'d', 'LARGE', 0"})
    public void testDeRegisterCar(String plate, Size size, int preferredFloor){
        ParkingLot pl = new ParkingLot(2,2);
        Gate g = new Gate(pl);
        Car car = new Car(plate, size, preferredFloor);
        
        g.registerCar(car);
        g.deRegisterCar(car.getTicketId());
        
        Space[][] fp = pl.getFloorPlan();
        
        for(Space[] f : fp){
            for(Space s : f){
                assertEquals(false, s.isTaken());
            }
        }      
    }
}