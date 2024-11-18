package fr.uge.jee.onlineshop;

public class DroneDelivery implements Delivery{

    DroneDelivery(){

    }
    @Override
    public String description() {
        return "Delivery by drone";
    }
}
