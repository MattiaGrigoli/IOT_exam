package model;

import java.util.Random;

public class AccSensor {
    private Random random;
    private double acceleration;
    private BatterySensor battery;

    public AccSensor() {
        this.random = new Random(System.currentTimeMillis());
        this.acceleration = 0;
        this.battery = new BatterySensor();
    }

    private void generateAcceleration()
    {
        acceleration = random.nextDouble(-10,10); //bounds from gemini, more than 5 is emergency
    }

    public double getAcceleration() {
        generateAcceleration();
        return acceleration;
    }

    public double getBattery() {
        return battery.getCharge();
    }

    @Override
    public String toString() {
        return "AccSensor{" +
                "acceleration=" + acceleration +
                ", battery=" + battery +
                '}';
    }
}
