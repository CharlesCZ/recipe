package guru.springframework.controllers;


import guru.springframework.commands.RecipeCommand;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class RecipeController {

private final RecipeService recipeService;




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
    public String  saveOrUpdate(@ModelAttribute("recipe") RecipeCommand recipe){
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


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException(Exception exception){

        log.error("Handling Number Format Exception");
        log.error(exception.getMessage());
        ModelAndView modelAndView=new ModelAndView("400error");
        modelAndView.addObject("exception",exception);

        return modelAndView;
    }



}

