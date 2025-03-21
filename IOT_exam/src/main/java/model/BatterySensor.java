package model;

import java.util.Random;

public class BatterySensor {
    private transient Random random;
    private double charge;

    public BatterySensor() {
        this.random = new Random(System.currentTimeMillis());
        this.charge = 0;
    }

    public void generateCharge ()
    {
        charge = random.nextDouble()*100;
    }

    public double getCharge() {
        return charge;
    }

    @Override
    public String toString() {
        return charge + "%";
    }
}
