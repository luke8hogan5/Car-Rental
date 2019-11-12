package Decorator.FourDoors;

import Decorator.VehicleDecorator;
import interfaces.Vehicle;

public class Hatchback extends VehicleDecorator {
    public Hatchback(Vehicle vehicle) {
        super(vehicle);
    }

    @Override
    public double rentRate() {
        return super.rentRate()+10;
    }
}
