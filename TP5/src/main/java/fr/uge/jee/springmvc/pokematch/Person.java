package fr.uge.jee.springmvc.pokematch;

import javax.validation.constraints.Pattern;

public record Person(
    @Pattern(regexp="^[a-zA-Z\\s-]+$", message="Firstname should only contains lowercase & uppercase letters")
    String firstName,
    @Pattern(regexp = "^[a-zA-Z\\s-]+$", message = "Lastname should only contains lowercase & uppercase letters")
    String lastName){


    @Override
    public int hashCode() {
        return firstName.hashCode() + lastName.hashCode();
    }
}
