package com.mahmoud.drone.model;

public class DroneFactory {
    public static Drone createDrone(String type, Double weight) {
        if (type.equalsIgnoreCase("lightweight") && weight >0 && weight <=100  ) {
            return new LightweightDrone();
        } else if (type.equalsIgnoreCase("heavyweight") && weight >=400 && weight <=500) {
            return new HeavyweightDrone();
        } else if(type.equalsIgnoreCase("middleweight") && weight >100 && weight <=200){
            return new CruiserweightDrone();
        }else if(type.equalsIgnoreCase("cruiserweight") && weight >200 && weight <=300){
            return new CruiserweightDrone();
        }else {
            return null;
        }
    }
}