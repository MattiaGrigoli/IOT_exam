package model;

import java.util.Random;

public class MovementSensor {
    private transient Random random;
    private boolean technique; //good == true or bad == false
    private double steps; //steps/min
    private BatterySensor battery;

    public  MovementSensor() {
        this.random = new Random(System.currentTimeMillis());
        this.technique = false;
        this.steps = 0;
        this.battery = new  BatterySensor();
    }

    public void generateTechinque()
    {
        technique = random.nextBoolean();
    }

    public void generateSteps()
    {
        steps = 60 + (250 - 60) * random.nextDouble();
    }

    public boolean isTechnique() {
        return technique;
    }

    public double getSteps() {
        return steps;
    }

    public double getBattery() {
        return battery.getCharge();
    }

    public void generateBattery() {battery.generateCharge();}
    @Override
    public String toString() {
        return "MovementSensor{" +
                "technique=" + technique +
                ", steps=" + steps +
                ", battery=" + battery +
                '}';
    }
}
