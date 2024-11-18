package fr.uge.jee.onlineshop;

import java.util.Objects;

public class RefundInsurance implements Insurance{

    private boolean member;

    public RefundInsurance(boolean isMember){
        this.member = isMember;
    }

    public RefundInsurance(){}

    @Override
    public String description() {
        return member?"Return insurance only for members ": "Return insurance";
    }
}
