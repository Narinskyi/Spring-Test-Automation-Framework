package com.onarinskyi._testdata_.gui.model;

public class Car {
    private String name;
    private int guests;
    private int doors;
    private int baggage;
    private CarGear gear;
    private CarType type;

    private int rating;

    public static final Car CAMRY = new Car("Toyota Camry",4,2,2, CarGear.AUTO, CarType.LUXURY, 4);
    public static final Car ASTRA = new Car("Opel Astra", 4,2,1, CarGear.MANUAL, CarType.STANDARD, 4);

    public Car(String name, int guests, int doors, int baggage, CarGear gear, CarType type, int rating) {
        this.name = name;
        this.guests = guests;
        this.doors = doors;
        this.baggage = baggage;
        this.gear = gear;
        this.type = type;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public int getGuests() {
        return guests;
    }

    public int getDoors() {
        return doors;
    }

    public int getBaggage() {
        return baggage;
    }

    public CarGear getGear() {
        return gear;
    }

    public CarType getType() {
        return type;
    }

    public int getRaiting() {
        return rating;
    }
}
