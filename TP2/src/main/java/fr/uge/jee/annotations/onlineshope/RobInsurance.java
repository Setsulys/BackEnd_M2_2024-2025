package fr.uge.jee.annotations.onlineshope;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class RobInsurance implements Insurance{
    @Value("${onlineshop.returninsurance.membersonly}")
    private boolean member;
    @Override
    public String description() {
        return member?"Return insurance only for members ":"Thief insurance";
    }
}
