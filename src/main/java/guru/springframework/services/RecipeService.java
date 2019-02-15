package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    public Set<Recipe> getRecipes();

    public Recipe findById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    public RecipeCommand findCommandById(Long l);

    void deleteById(Long l);
}
