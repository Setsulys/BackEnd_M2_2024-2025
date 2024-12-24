package fr.uge.jee.springmvc.pokematch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//@Service
//public class BestPokemonTracker {
//    private final Lock lock = new ReentrantLock();
//    private final HashMap<Pokemon, Integer> pokemonCountMap = new HashMap<>();
//    private final int maxPokemonCount;
//
//    public BestPokemonTracker(@Value("${pokemon.top.count}") int maxPokemonCount) {
//        this.maxPokemonCount = maxPokemonCount;
//    }
//
//    public void increment(Pokemon pokemon) {
//        lock.lock();
//        try{
//            pokemonCountMap.put(pokemon, pokemonCountMap.getOrDefault(pokemon,1) + 1);
//            System.out.println(pokemonCountMap);
//        }finally {
//            lock.unlock();
//        }
//    }
//
//    public List<Pokemon> getTopPokemons() {
//        lock.lock();
//        try {
//            return pokemonCountMap.entrySet().stream()
//                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
//                    .limit(maxPokemonCount)
//                    .map(Map.Entry::getKey)
//                    .toList();
//        }finally {
//            lock.unlock();
//        }
//    }
//}
