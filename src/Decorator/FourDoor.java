package Decorator;

import interfaces.Vehicle;

public class FourDoor implements Vehicle{
    @Override
    public double rentRate() {
        return 30;
    }
}
