package fr.uge.jee.annotations.onlineshope;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RefundInsurance implements Insurance{
    @Value("${onlineshop.returninsurance.membersonly}")
    private boolean member;
    @Override
    public String description() {
        return member?"Return insurance only for members ": "Return insurance";
    }
}
