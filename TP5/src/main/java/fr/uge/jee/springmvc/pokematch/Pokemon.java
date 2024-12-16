package fr.uge.jee.springmvc.pokematch;

import java.util.List;

public record Pokemon(

    List<PokemonResult> results
    ){ }
