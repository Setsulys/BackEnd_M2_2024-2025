package fr.uge.jee.springmvc.pokematch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import java.io.ByteArrayOutputStream;

import java.net.URL;


@Service
public class PokemonServices {

    private final List<Pokemon> pokemons;
    private final WebClient webClient;

    private final Lock lock = new ReentrantLock();
    private final HashMap<Pokemon, Integer> pokemonCountMap = new HashMap<>();
    private final int maxPokemonCount;
    private final HashMap<Pokemon,String> pokemonImageCache = new HashMap<>();
    private record PokemonResponse(List<Pokemon> results) {
    }

    public PokemonServices(WebClient webClient,@Value("${pokemon.top.count}") int maxPokemonCount) {
        this.webClient = webClient;
        this.pokemons = fetchPokemons();
        this.maxPokemonCount = maxPokemonCount;
    }

    /**
     * Fetching pokemon from the api
     * @return
     */
    private List<Pokemon> fetchPokemons() {
        var response = webClient.get()
                .uri("https://pokeapi.co/api/v2/pokemon?limit=151")
                .retrieve()
                .bodyToMono(PokemonResponse.class)
                .block();
        return response.results();
    }

    /**
     * Compute person firstname & last name hashcode and find the nearest pokemon name hashcode
     * @param person
     * @return
     */
    public Pokemon findFavoritePokemon(Person person){
        Objects.requireNonNull(person);
        if(pokemons == null || pokemons.isEmpty()){
            throw new IllegalStateException("No pokemons found");
        }


        Pokemon bestPokemon = pokemons.get(0);
        int minDifference = Math.abs(bestPokemon.hashCode() - person.hashCode());
        for (Pokemon pokemon : pokemons) {
            int difference = Math.abs(pokemon.hashCode() - person.hashCode());
            if (difference < minDifference) {
                bestPokemon=pokemon;
                minDifference = difference;
            }
        }
        incrementPopularity(bestPokemon);
        return bestPokemon;
    }


    /// Popularity of pokemons
    /**
     * Increment Pokemon popularity by person if the person haven't yet used the application
     * @param pokemon
     */
    private void incrementPopularity(Pokemon pokemon) {
        lock.lock();
        try{
            pokemonCountMap.put(pokemon, pokemonCountMap.getOrDefault(pokemon,0) + 1);
            System.out.println(pokemonCountMap);
        }finally {
            lock.unlock();
        }
    }

    /**
     * Show Top 10 pokemons that were designed as fetiche in the database
     * @return
     */
    public List<Pokemon> getTopPokemons() {
        lock.lock();
        try {
            return pokemonCountMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .limit(maxPokemonCount)
                    .map(Map.Entry::getKey)
                    .toList();
        }finally {
            lock.unlock();
        }
    }

    /// Show picture of pokemon
    /**
     * Get the pokemon sprite url to show it to user with the url
     * @param pokemon
     * @return
     */
    public String getImageUrl(Pokemon pokemon){
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+pokemon.id()+".png";
    }

    /// Show image of pokemon by downloading

    /**
     * Check if the pokemon was already downloaded in the cache if not it will download
     * @param pokemon
     * @return
     */
    public String getPokemonImage(Pokemon pokemon){
        return pokemonImageCache.putIfAbsent(pokemon, downloadImage(pokemon));
    }


    /**
     * Download the pokemon Image at ressources/static/images/.
     * @param pokemon
     * @return
     */
    private String downloadImage(Pokemon pokemon){
        String savedir = "src/main/resources/static/images";
        String fileName = pokemon.toString()+".png";
        Path targetPath = Paths.get(savedir,fileName);
        try{
            var url = new URL(getImageUrl(pokemon));
            try(InputStream in = url.openStream()){
                Files.copy(in, targetPath, StandardCopyOption.REPLACE_EXISTING);
            }
            return "/images/"+fileName;
        }catch(IOException e){
            System.out.println("Couldn't download image");
            return null;
        }
    }
}