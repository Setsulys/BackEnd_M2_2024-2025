package fr.uge.jee.annotations.onlineshope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Set;

//@Configuration
//public class Config {
//
//    @Bean
//    StandardDelivery standardDelivery(){
//        return new StandardDelivery(999);
//    }
//
//    @Bean
//    RefundInsurance returnInsurance(){
//        return new RefundInsurance();
//    }
//
//    @Bean
//    RobInsurance robInsurance(){
//        return new RobInsurance();
//    }
//
//    @Bean
//    OnlineShop onlineShop(){
//        return new OnlineShop("AhMaZone", Set.of(standardDelivery()),Set.of(returnInsurance(),robInsurance()));
//    }
//
//}

@Configuration
@ComponentScan("fr.uge.jee.annotations")
@PropertySource("classpath:onlineshop.properties")
public class Config {


}