package fr.uge.jee.servlet.rectanglesession;

import fr.uge.jee.springmvc.rectangle.Rectangle;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class rectangleController {
    private ArrayList<Rectangle> rectangles= new ArrayList<>();
    @GetMapping("/rectangle")
    public String rectangle( Model model){
        return "rectangle-form";
    }

    @PostMapping("/rectangle")
    public String processForm(@ModelAttribute("parameter") Rectangle parameter,
                              BindingResult result,
                              Model model, HttpSession session){
        if (result.hasErrors()){
            return "error";
        }
        if (parameter.getWidth()<0 || parameter.getHeight()<0){
            return "error";
        }
        if(!session.isNew()){
            rectangles.add(parameter);
        }
        else{
            rectangles.clear();
        }
        model.addAttribute("area", parameter.area());
        return "parameter-result";
    }
}
