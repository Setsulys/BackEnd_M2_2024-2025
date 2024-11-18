package fr.uge.jee.onlineshop;

import java.util.Objects;
import java.util.Set;

public class OnlineShop {

    private final String name;
    private final Set<Delivery> deliveryOptions;
    private final Set<Insurance> insurances;

    OnlineShop(String shop,Set<Delivery> deliveries,Set<Insurance> insurances){
        this.name = Objects.requireNonNull(shop);
        this.deliveryOptions=Objects.requireNonNull(deliveries);
        this.insurances = Objects.requireNonNull(insurances);
    }


    public void printDescription(){
        System.out.println(name);
        System.out.println("Delivery options");
        deliveryOptions.forEach(opt -> System.out.println("\t"+opt.description()));
        System.out.println("Insurance options");
        insurances.forEach(insurance -> System.out.println("\t"+insurance.description()));
    }
}