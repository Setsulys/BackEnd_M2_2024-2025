package fr.uge.jee;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="Pokemon")
public class Pokemon {

    @Id
    String name;
    int score;

    public Pokemon(){

    }
    public Pokemon(String name){
        this.name= Objects.requireNonNull(name);
        this.score=0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }
}
