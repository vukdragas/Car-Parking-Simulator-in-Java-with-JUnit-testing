package parking;

import parking.facility.Space;
import vehicle.Size;

public class ParkingLot{
    private final Space[][] floorPlan;
    
    public ParkingLot(int floorNumber, int spaceNumber) throws IllegalArgumentException{
        if(floorNumber < 1 || spaceNumber < 1) throw new IllegalArgumentException("Number of floors or spaces has to be greater then 1");
        
        this.floorPlan = new Space[floorNumber][spaceNumber];
        
        for(int i = 0; i < floorNumber; i++){
            for(int j = 0; j < spaceNumber; j++){
                floorPlan[i][j] = new Space(i, j);
            }
        }
    }

    public Space[][] getFloorPlan(){
        return this.floorPlan;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        for(int i = floorPlan.length-1; i >= 0; i--){
            for(Space s : floorPlan[i]){
                if(!s.isTaken()){
                        sb.append("X").append(" ");
                }
                else{
                    if(s.getOccupyingCarSize() == Size.SMALL){
                        sb.append("S").append(" ");
                    }
                    else{
                        sb.append("L").append(" ");
                    }
                }
            }
            sb.append("\n");
        }

        String result = sb.toString();
        return result;
    }
}