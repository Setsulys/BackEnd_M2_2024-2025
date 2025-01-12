package fr.uge.jee.springmvc.pokematch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class PokemonServices {

    private final List<Pokemon> pokemons;
    private final WebClient webClient;

    private final Lock lock = new ReentrantLock();
    private final HashMap<Pokemon, Integer> pokemonCountMap = new HashMap<>();
    private final int maxPokemonCount;
    private final HashMap<Pokemon,String> pokemonImageCache = new HashMap<>();
    private final HashMap<Pokemon,byte[]> imageCache = new HashMap<>();


    private record PokemonResponse(List<Pokemon> results) {

    }
    private record GraphQLPokemonResponse(Data data) {
        record Data(List<PokemonEntry> pokemon_v2_pokemon) {}
        record PokemonEntry(String name,String url, int id) {
        }
        /**
         * Get data of data and put it in a list of pokemon
         * @return
         */
        public List<Pokemon> pokemons() {
            return data.pokemon_v2_pokemon().stream()
                    .map(e-> new Pokemon(e.name, e.id))
                    .toList();
        }
    }


    public PokemonServices(WebClient webClient,@Value("${pokemon.top.count}") int maxPokemonCount) {
        this.webClient = webClient;
//        this.pokemons = fetchPokemons();
        this.pokemons = fetchPokemonsFromGraphQL();
        this.maxPokemonCount = maxPokemonCount;
    }

    /**
     * Fetching pokemon from the api
     * @return
     */
    private List<Pokemon> fetchPokemons() {
        var response = webClient.get()
                .uri("https://pokeapi.co/api/v2/pokemon?limit=40")
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
        var bestPokemon= pokemons.stream().min(Comparator.comparing(e->Math.abs(e.hashCode() - person.hashCode()))).orElse(null);
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
     * check if the image is in the cache, if not, it will download the image.
     * After all it will convert image to string with base64 encoder
     * @param pokemon
     * @return
     */
    public String getPokemonImage64(Pokemon pokemon){
        byte[] imageBytes = imageCache.get(pokemon);
        if(imageBytes == null){
            System.out.println("Downloading image...");
            imageBytes = downloadbyteImage(pokemon);
            imageCache.put(pokemon, imageBytes);
        }
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    /**
     * Download image in byte array form
     * @param pokemon
     * @return
     */
    public byte[] downloadbyteImage(Pokemon pokemon){
        Mono<byte[]> imageBytesMono = webClient.get()
                .uri(getImageUrl(pokemon))
                .retrieve()
                .bodyToMono(byte[].class);
        return imageBytesMono.block();
    }

    /**
     * Fetching pokemon from graphql
     * @return
     */
    private List<Pokemon> fetchPokemonsFromGraphQL() {
        String query = """
        {
          pokemon_v2_pokemon(limit: 40) {
            name
            id
          }
        }
        """;
        var response = webClient.post()
                .uri("https://beta.pokeapi.co/graphql/v1beta")
                .header("Content-Type", "application/json")
                .bodyValue(Map.of("query", query))
                .retrieve()
                .bodyToMono(GraphQLPokemonResponse.class)
                .block();
        return response.pokemons();
    }

}