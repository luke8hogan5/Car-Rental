package Decorator;

import interfaces.Vehicle;

public class Van implements Vehicle {
    @Override
    public double rentRate() {
        return 55;
    }
}
