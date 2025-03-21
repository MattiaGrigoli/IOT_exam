package model;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;

// just a class for the Json to send to the wearable containing all statistics
public class Statitics {
    private Dictionary<String, Double> batteries;
    private double acceleration;
    private double[] coordinates;
    private byte heartRate;
    private boolean technique;
    private double steps;
    private double[] means; //heart, steps and acceleration

    public Statitics() {
        batteries = new Hashtable<String, Double>();
        coordinates = new double[3];
        heartRate = 0;
        steps = 0;
        means = new double[3];
        technique = true;
    }

    public Dictionary<String, Double> getBatteries() {
        return batteries;
    }

    public void setBatteries(Dictionary<String, Double> batteries) {
        this.batteries = batteries;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public byte getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(byte heartRate) {
        this.heartRate = heartRate;
    }

    public boolean isTechnique() {
        return technique;
    }

    public void setTechnique(boolean technique) {
        this.technique = technique;
    }

    public double getSteps() {
        return steps;
    }

    public void setSteps(double steps) {
        this.steps = steps;
    }

    public double[] getMeans() {
        return means;
    }

    public void setMeans(double[] means) {
        this.means = means;
    }

    public void setMeanstep(double mean) {
        this.means[1] = mean;
    }
    public void setMeansbeats(double mean) {
        this.means[0] = mean;
    }
    public void setMeanacc(double mean) {
        this.means[2] = mean;
    }

    @Override
    public String toString() {
        return "Statitics{" +
                "batteries=" + batteries +
                ", acceleration=" + acceleration +
                ", coordinates=" + Arrays.toString(coordinates) +
                ", heartRate=" + heartRate +
                ", technique=" + technique +
                ", steps=" + steps +
                ", means=" + Arrays.toString(means) +
                '}';
    }
}
