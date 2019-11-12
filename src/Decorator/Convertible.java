package Decorator;

import interfaces.Vehicle;

public class Convertible implements Vehicle{
    @Override
    public double rentRate() {
        return 30;
    }
}
