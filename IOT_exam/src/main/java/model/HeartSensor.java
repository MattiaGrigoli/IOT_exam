package model;

import java.util.Random;

public class HeartSensor {

    private transient Random random;
    private byte heartRate;
    private BatterySensor battery;

    public HeartSensor() {
        this.random = new Random(System.currentTimeMillis());
        this.heartRate = 0;
        this.battery = new BatterySensor();
    }

    public void generateHeartRate() { heartRate = (byte) (60+random.nextInt(100)); }

    public byte getHeartRate() {
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
