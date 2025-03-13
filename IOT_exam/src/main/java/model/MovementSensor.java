package model;

import java.util.Random;

public class MovementSensor {
    private Random random;
    private boolean technique; //good or bad
    private double steps; //steps/min
    private BatterySensor battery;

    public  MovementSensor() {
        this.random = new Random(System.currentTimeMillis());
        this.technique = false;
        this.steps = 0;
        this.battery = new  BatterySensor();
    }

    private void generateTechinque()
    {
        technique = random.nextBoolean();
    }

    private void generateSteps()
    {
        steps = random.nextDouble(60,250);
    }

    public boolean isTechnique() {
        generateTechinque();
        return technique;
    }

    public double getSteps() {
        generateSteps();
        return steps;
    }

    public double getBattery() {
        return battery.getCharge();
    }

    @Override
    public String toString() {
        return "MovementSensor{" +
                "technique=" + technique +
                ", steps=" + steps +
                ", battery=" + battery +
                '}';
    }
}
