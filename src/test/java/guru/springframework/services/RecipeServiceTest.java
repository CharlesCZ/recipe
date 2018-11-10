package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RecipeServiceTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        recipeService= new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipes() {

Recipe recipe=new Recipe();
recipe.setDescription("nanana");
        HashSet receipesData= new HashSet();
        receipesData.add(recipe);
        Recipe recipe2=new Recipe();
        receipesData.add(recipe2);
        when(recipeRepository.findAll()).thenReturn(receipesData);




        Set<Recipe> recipes=recipeService.getRecipes();

        assertEquals(2,recipes.size());
        verify(recipeRepository, times(1)).findAll();
    }
}