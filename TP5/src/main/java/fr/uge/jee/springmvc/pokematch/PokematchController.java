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
    private List<Pokemon> topPokemon;

    @Autowired
    PokematchController(PokemonServices pokemonServices){
        this.pokemonServices = pokemonServices;
    }

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
        model.addAttribute("bestpokemons",topPokemon);
        if(bindingResult.hasErrors()){
            return "index";
        }
        var pokemon = pokemonServices.findFavoritePokemon(parameter);
        topPokemon = pokemonServices.getTopPokemons();
        model.addAttribute("pokemonImage",pokemonServices.getImageUrl(pokemon));
        model.addAttribute("bestpokemons",topPokemon);
        model.addAttribute("imagedlbyte",pokemonServices.getPokemonImage64(pokemon));
        model.addAttribute("favouritePokemon", pokemon);
        return "index";
    }

}
