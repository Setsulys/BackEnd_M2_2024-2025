package fr.uge.jee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.transaction.Transactional;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    @PersistenceUnit
    private final EntityManagerFactory emf;

    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository,
                           EntityManagerFactory emf,
                           EntityManager em){
        this.pokemonRepository = pokemonRepository;
        this.emf = emf;
        this.em = em;
    }

    @Transactional
    public void insertOrIncrementPokemon(String name) {
        var pokemon = em.find(Pokemon.class,name, LockModeType.PESSIMISTIC_WRITE);
        if(pokemon==null){
            pokemon =new Pokemon(name);
        }
        pokemon.setScore(pokemon.getScore()+1);
        pokemonRepository.save(pokemon);
    }
    public long totalCountVote() {
        return 0L;
    }
}
