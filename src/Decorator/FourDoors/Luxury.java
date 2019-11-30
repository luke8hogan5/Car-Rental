package Decorator.FourDoors;

import Decorator.VehicleDecorator;
import interfaces.Vehicle;

public class Luxury extends VehicleDecorator {
    public Luxury(Vehicle vehicle) {
        super(vehicle);
    }

    @Override
    public double rentRate() {
        return super.rentRate()+30;
    }
}
