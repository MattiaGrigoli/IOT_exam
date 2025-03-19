package model;

import java.util.Dictionary;

// just a class for the Json to send to the wearable containing all statistics
public class Statitics {
    private Dictionary<String, Double> batteries;
    private double acceleration;
    private double[] coordinates;
    private byte heartRate;
    private boolean technique;
    private double steps;
    private double[] means; //heart and steps
}
