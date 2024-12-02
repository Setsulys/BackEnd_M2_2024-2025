package fr.uge.jee.springmvc.rectangle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.service.annotation.GetExchange;

@Controller
public class rectangleController {

    @GetMapping("/rectangle")
    public String rectangle( Model model){
        return "rectangle-form";
    }

    @PostMapping("/rectangle")
    public String processForm(@ModelAttribute("parameter") Rectangle parameter,
                              BindingResult result,
                              Model model){
        if (result.hasErrors()){
            return "error";
        }
        if (parameter.getWidth()<0 || parameter.getHeight()<0){
            return "error";
        }
        model.addAttribute("area", parameter.area());
        return "parameter-result";
    }
}
