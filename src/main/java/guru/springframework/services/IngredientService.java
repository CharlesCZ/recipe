package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


public interface IngredientService {

    Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    Mono<IngredientCommand> saveIngredientCommand(IngredientCommand command);

    Mono<Void> deleteById(String recipeId, String idToDelete);
}

