package Decorator.FourDoors;

import Decorator.VehicleDecorator;
import interfaces.Vehicle;

public class SUV extends VehicleDecorator {
    public SUV(Vehicle vehicle) {
        super(vehicle);
    }

    @Override
    public double rentRate() {
        return super.rentRate()+20;
    }
}
