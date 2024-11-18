package fr.uge.jee.annotations.onlineshope;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class StandardDelivery implements Delivery{

    @Value("${onlineshop.standarddelivery.delay}")
    private int delay;


    @Override
    public String description() {
        return "Standard Delivery with a delay of "+delay+" days";
    }
}
