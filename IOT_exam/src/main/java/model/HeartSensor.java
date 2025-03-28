package model;

import java.util.Random;

public class HeartSensor {

    private transient Random random;
    private int heartRate;
    private BatterySensor battery;

    public HeartSensor() {
        this.random = new Random(System.currentTimeMillis());
        this.heartRate = 0;
        this.battery = new BatterySensor();
    }

    public void generateHeartRate() {
        heartRate = 60+random.nextInt(140);
        if(heartRate < 0){
            heartRate = heartRate * (-1);
        }
    }

    public int getHeartRate() {
        return heartRate;
    }

    public double getBattery() {
        return battery.getCharge();
    }

    public void generateBattery() {battery.generateCharge();}
    @Override
    public String toString() {
        return "HeartSensor{" +
                "heartRate=" + heartRate +
                ", battery=" + battery  +
                '}';
    }
}
