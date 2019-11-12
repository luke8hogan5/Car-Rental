package Decorator;

import interfaces.Vehicle;

abstract public class VehicleDecorator implements Vehicle {
    private Vehicle vehicle;

    public VehicleDecorator(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public double rentRate() {
        return vehicle.rentRate();
    }
}
