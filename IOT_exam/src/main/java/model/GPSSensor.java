package model;

import java.util.Arrays;
import java.util.Random;

public class GPSSensor {
    private transient Random random;
    private double coordinates[]; //latitude,longitude and altitude
    private BatterySensor battery;

    public GPSSensor()
    {
        this.random = new Random(System.currentTimeMillis());
        this.coordinates = new double[3];
        this.battery = new BatterySensor();
    }

    public void generateCoordinates()
    {
        coordinates[0] = (-90) + (90 - (-90)) *random.nextDouble();
        coordinates[1] = random.nextDouble() * 360;
        coordinates[2] = random.nextDouble() * 29029;
    }

    public double getBattery()
    {
        return battery.getCharge();
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void generateBattery() {battery.generateCharge();}

    @Override
    public String toString() {
        return "GPSSensor{" +
                "coordinates=" + Arrays.toString(coordinates) +
                ", battery=" + battery +
                '}';
    }
}
