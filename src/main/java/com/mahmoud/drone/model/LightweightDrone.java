package com.mahmoud.drone.model;

public class LightweightDrone implements Drone {


    @Override
    public void fly() {
        System.out.println("Flying a lightweight drone");
    }
}
