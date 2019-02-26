package guru.springframework.controllers;


import guru.springframework.commands.RecipeCommand;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class RecipeController {

private final RecipeService recipeService;

private static final String RECIPE_RECIPEFORM_URL="recipe/recipeform";



    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping("/recipe/{id}/show")
    public String ShowById(@PathVariable String id,Model model){
    model.addAttribute("recipe",recipeService.findById(new Long(id)));
    return "recipe/show";
    }


    @GetMapping("/recipe/new")
    public String newRecipe(Model model){
    model.addAttribute("recipe",new RecipeCommand());
    return "recipe/recipeform";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(Model model,@PathVariable Long id){
        model.addAttribute("recipe",recipeService.findCommandById(id));
        return "recipe/recipeform";
    }

    @PostMapping("/recipe/new")
    public String  saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand recipe, BindingResult bindingResult){

   if(bindingResult.hasErrors()){

       bindingResult.getAllErrors().forEach(objectError ->
       {log.debug(objectError.toString());
       });

       return RECIPE_RECIPEFORM_URL;
   }

    RecipeCommand recipeCommand=recipeService.saveRecipeCommand(recipe);
    return "redirect:/recipe/"+recipeCommand.getId()+ "/show";
    }

@GetMapping("/recipe/{id}/delete")
public String deleteById(@PathVariable Long id){
        log.debug("Deleting id:  "+ id);
        recipeService.deleteById(id);

        return "redirect:/";
}

@ResponseStatus(HttpStatus.NOT_FOUND)
@ExceptionHandler(NotFoundException.class)
public ModelAndView handleNotFound(Exception exception){

log.error("Handling not found exception");
log.error(exception.getMessage());
    ModelAndView modelAndView=new ModelAndView("404error");
    modelAndView.addObject("exception",exception);

        return modelAndView;
}






}

