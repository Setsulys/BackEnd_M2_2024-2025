package fr.uge.jee.springmvc.pokematch;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public record Person(
    @Pattern(regexp="^[a-z\\s-]+$", message="Firstname should only contains lowercase letters")
    String firstName,
    @Pattern(regexp = "^[a-z\\s-]+$", message = "Lastname should only contains lowercase letters")
    String lastName){


    @Override
    public int hashCode() {
        return firstName.hashCode() + lastName.hashCode();
    }
}
