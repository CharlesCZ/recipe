package guru.springframework.controllers;


import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class IngredientController {

    RecipeService recipeService;

    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/ingredients")
    public String listIngredients(@PathVariable Long id,Model model){
        log.debug("Getting ingredient list for recipe id:  "+id);


        //use commands to avoid lazy load errors in Thymeleaf.
        model.addAttribute("recipe",recipeService.findCommandById(id));
        return  "recipe/ingredient/list";
    }
}
