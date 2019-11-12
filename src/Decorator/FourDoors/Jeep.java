package Decorator.FourDoors;

import Decorator.FourDoor;
import Decorator.VehicleDecorator;
import interfaces.Vehicle;

public class Jeep extends VehicleDecorator {

    public Jeep(Vehicle vehicle) {
        super(vehicle);
    }

    @Override
    public double rentRate() {
        return super.rentRate()+5;
    }
}
