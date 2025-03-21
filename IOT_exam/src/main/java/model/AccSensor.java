package model;

import java.util.Random;

public class AccSensor {
    private transient Random random;
    private double acceleration;
    private BatterySensor battery;
    private int min = -10, max = 10;

    public AccSensor() {
        this.random = new Random(System.currentTimeMillis());
        this.acceleration = 0;
        this.battery = new BatterySensor();
    }

    public void generateAcceleration()
    {
        acceleration = min + (max - min ) * random.nextDouble(); //bounds from gemini, more than 5 is emergency
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getBattery() {
        return battery.getCharge();
    }

    public void generateBattery() {battery.generateCharge();}

    @Override
    public String toString() {
        return "AccSensor{" +
                "acceleration=" + acceleration +
                ", battery=" + battery +
                '}';
    }
}
