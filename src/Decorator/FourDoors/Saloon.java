package Decorator.FourDoors;

import Decorator.FourDoor;
import Decorator.VehicleDecorator;
import interfaces.Vehicle;

public class Saloon extends VehicleDecorator {
    public Saloon(Vehicle vehicle) {
        super(vehicle);
    }

    @Override
    public double rentRate() {
        return super.rentRate()+15;
    }
}
