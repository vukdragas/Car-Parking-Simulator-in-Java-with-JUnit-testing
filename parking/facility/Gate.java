package parking.facility;
import vehicle.Car;
import vehicle.Size;
import parking.ParkingLot;
import parking.facility.Space;
import java.util.List;
import java.util.ArrayList;

public class Gate{
    private final ArrayList<Car> cars;
    private final ParkingLot parkingLot;

    public Gate(ParkingLot parkingLot){
        this.cars = new ArrayList<>();
        this.parkingLot = parkingLot;
    }

    private Space findTakenSpaceByCar(Car c){
        if (c == null) return null;
        
        Space[][] fp = parkingLot.getFloorPlan();

        for (Space[] f : fp){
            for (Space s : f) {
                if (s.isTaken() && s.getCarLicensePlate().equals(c.getLicensePlate())) {
                    return s;
                }
            }
        }
        return null;
    }
    

    private Space findAvailableSpaceOnFloor(int floor, Car c){
        if (c == null || floor < 0 || floor >= parkingLot.getFloorPlan().length ) return null;

        Space[] f = parkingLot.getFloorPlan()[floor];

        for(int j = 0; j < f.length; j++){
            Space s = f[j];
            if(!s.isTaken()){
                if(c.getSpotOccupation()==Size.SMALL){
                    return s;
                }
                if(j+1 < f.length && !f[j+1].isTaken()){
                    return f[j+1];
                }
            }
        }
        return null;

    }

    public Space findAnyAvailableSpaceForCar(Car c){
        if (c == null) return null;

        Space[][] fp = parkingLot.getFloorPlan();
        for(int f = 0; f < fp.length; f++){
            Space s = findAvailableSpaceOnFloor(f,c);
            if(s != null) return s;
        }
        return null;
    }

    public Space findPreferredAvailableSpaceForCar(Car c){
        if(c == null) return null;

        int f = c.getPreferredFloor();
        Space s = findAvailableSpaceOnFloor(f,c);
        if(s != null) return s;

        int upflr = f+1;
        int dwnflr = f-1;
        Space[][] fp = parkingLot.getFloorPlan();

        while(upflr < fp.length || dwnflr >= 0){
            if(dwnflr >= 0){
                s = findAvailableSpaceOnFloor(dwnflr,c);
                dwnflr--;
                if(s != null) return s;
            }
            if(upflr < fp.length){
                s = findAvailableSpaceOnFloor(upflr,c);
                upflr++;
                if(s != null) return s;
            }
        }

        return null;
    }

    public boolean registerCar(Car c){
        if (c == null || c.getTicketId() != null) return false;

        Space s = findPreferredAvailableSpaceForCar(c);

        if(s != null){
            s.addOccupyingCar(c);
            Space[][] fp = parkingLot.getFloorPlan();

            if(c.getSpotOccupation()==Size.LARGE){
                Space ss = fp[s.getFloorNumber()][s.getSpaceNumber() - 1];
                ss.addOccupyingCar(c);
            }

            cars.add(c);
            c.setTicketId("ticket - " + c.getLicensePlate());
            return true;
        }

        return false;
    }

    public void registerCars(Car... cars){
        for(Car c : cars){
            boolean l = registerCar(c);
            if (!l) break;
        }
    }

    public void deRegisterCar(String ticketId){
        if (ticketId == null) return;
        
        Car car = null;
        for (Car c : cars) {
            if (ticketId.equals(c.getTicketId())) {
                car = c;
                break;
            }
        }
        
        Space[][] fp = parkingLot.getFloorPlan();
        Space s = findTakenSpaceByCar(car);
        s.removeOccupyingCar();

        if(car.getSpotOccupation()==Size.LARGE){
            Space ss = fp[s.getFloorNumber()][s.getSpaceNumber() + 1];
            ss.removeOccupyingCar();
        }
        
        cars.remove(car);
    }
}