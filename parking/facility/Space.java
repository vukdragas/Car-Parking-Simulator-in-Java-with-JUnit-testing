package parking.facility;

import vehicle.Car;
import vehicle.Size;

public class Space{
    private final int floorNumber;
    private final int spaceNumber;
    private Car occupyingCar;

    public Space(int floorNumber, int spaceNumber){
        this.floorNumber = floorNumber;
        this.spaceNumber = spaceNumber;
        this.occupyingCar = null;
    }

    public boolean isTaken(){
        return occupyingCar == null ? false : true;
    }
    
    public void addOccupyingCar(Car c){
        occupyingCar = c;
    }

    public void removeOccupyingCar(){
        occupyingCar = null;
    }

    public String getCarLicensePlate(){
        return occupyingCar.getLicensePlate();
    }

    public Size getOccupyingCarSize(){
        return occupyingCar.getSpotOccupation();
    }

    public int getFloorNumber(){
        return floorNumber;
    }

    public int getSpaceNumber(){
        return spaceNumber;
    }
}