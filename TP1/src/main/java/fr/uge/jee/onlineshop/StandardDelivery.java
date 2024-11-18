package fr.uge.jee.onlineshop;

public class StandardDelivery implements Delivery{

    private int delay;

    StandardDelivery(int value){
        delay = value;
    }

    @Override
    public String description() {
        return "Standard Delivery with a delay of "+delay+" days";
    }
}
