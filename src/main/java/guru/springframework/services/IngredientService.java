package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import org.springframework.stereotype.Service;


public interface IngredientService {
    IngredientCommand  findByRecipeIdAndIngredientId(Long recipeId,Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

    void deleteById(Long recipeId, Long id);

}