package fr.uge.jee.springmvc.pokematch;

import javax.validation.constraints.Pattern;

public record Person(
    @Pattern(regexp="^[a-zA-Z\\s-]+$", message="{firstName.invalid}")
    String firstName,
    @Pattern(regexp = "^[a-zA-Z\\s-]+$", message = "{lastName.invalid}")
    String lastName){


    @Override
    public int hashCode() {
        return firstName.hashCode() + lastName.hashCode();
    }
}
