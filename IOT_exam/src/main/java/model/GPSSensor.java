package model;

import java.util.Arrays;
import java.util.Random;

public class GPSSensor {
    private Random random;
    private double coordinates[]; //latitude,longitude and altitude
    private BatterySensor battery;

    public GPSSensor()
    {
        this.random = new Random(System.currentTimeMillis());
        this.coordinates = new double[3];
        this.battery = new BatterySensor();
    }

    private void generateCoordinates()
    {
        coordinates[0] = random.nextDouble(-90, 90);
        coordinates[1] = random.nextDouble(360);
        coordinates[2] = random.nextDouble(29029);
    }

    public double getBattery()
    {
        return battery.getCharge();
    }

    public double[] getCoordinates() {
        generateCoordinates();
        return coordinates;
    }

    @Override
    public String toString() {
        return "GPSSensor{" +
                "coordinates=" + Arrays.toString(coordinates) +
                ", battery=" + battery +
                '}';
    }
}
