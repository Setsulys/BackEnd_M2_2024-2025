package fr.uge.jee.onlineshop;

public class RobInsurance implements Insurance{

    private boolean member;

    public RobInsurance(boolean isMember){
        this.member = isMember;
    }

    public RobInsurance(){}

    @Override
    public String description() {
        return member?"Return insurance only for members ":"Thief insurance";
    }
}
