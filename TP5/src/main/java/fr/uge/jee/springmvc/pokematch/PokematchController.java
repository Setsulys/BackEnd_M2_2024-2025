package fr.uge.jee.springmvc.pokematch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PokematchController {
    private final PokemonServices pokemonServices;

    @Autowired
    PokematchController(PokemonServices pokemonServices){
        this.pokemonServices = pokemonServices;
    }
    //@Autowired
    //BestPokemonTracker bestPokemonTracker;

    /**
     * Get mapping to show the form of the application
     * @param param
     * @param bindingResult
     * @param model
     * @return
     */
    @GetMapping("/pokematch")
    public String pokematchForm(@ModelAttribute("person") @Valid Person param,BindingResult bindingResult,Model model) {
        return "index";
    }

    /**
     *  Post Mapping to show result of the computation
     * @param parameter
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/pokematch")
    public String processForm(@Valid @ModelAttribute Person parameter, BindingResult bindingResult, Model model){
        var pokemon = pokemonServices.findFavoritePokemon(parameter);

        model.addAttribute("bestpokemons",pokemonServices.getTopPokemons());
        model.addAttribute("pokemonImage",pokemonServices.getImageUrl(pokemon));
        model.addAttribute("pokemonImageDl",pokemonServices.getPokemonImage(pokemon));
        if(bindingResult.hasErrors()){
            return "index";
        }
        model.addAttribute("favouritePokemon", pokemon);
        return "index";
    }
}
