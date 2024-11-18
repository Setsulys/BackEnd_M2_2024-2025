package fr.uge.jee.annotations.onlineshope;

import org.springframework.stereotype.Component;

@Component
public class DroneDelivery implements Delivery{

    @Override
    public String description() {
        return "Delivery by drone";
    }
}
