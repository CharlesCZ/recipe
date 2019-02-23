package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

  public  RecipeServiceImpl recipeService;

    @Mock
    public  RecipeRepository recipeRepository;

    @Mock
    public  RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    public  RecipeCommandToRecipe recipeCommandToRecipe;
    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        recipeService=new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipes() throws Exception{
        Recipe recipe=new Recipe();
HashSet recipesData= new HashSet();
recipesData.add(recipe);

when(recipeRepository.findAll()).thenReturn(recipesData);

Set<Recipe> recipes=recipeService.getRecipes();
assertEquals(1,recipes.size());
verify(recipeRepository,times(1)).findAll();


    }


    @Test
    public void getRecipeByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }


    @Test(expected = NotFoundException.class)
    public void getRecipeByIdTestNotFound(){

        Optional<Recipe> recipeOptional=Optional.empty();

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned=recipeService.findById(1L);
    }
    @Test
    public void getRecipesTest() throws Exception {

        Recipe recipe = new Recipe();
        HashSet receipesData = new HashSet();
        receipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(receipesData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());
    }

    @Transactional
    @Test
    public void findCommandById() {
        RecipeCommand command=new RecipeCommand();
        command.setId(1L);

        when(recipeToRecipeCommand.convert(any())).thenReturn(command);

        Recipe recipe=new Recipe();
        recipe.setId(1L);

        assertEquals(command.getId(),recipeToRecipeCommand.convert(recipe).getId());
    }

    @Test
    public void testDeleteById() {
        //given
        Long idToDelete=2L;

        //when
        recipeService.deleteById(idToDelete);

        //no 'when' since return has void return statement
//then
        verify(recipeRepository,times(1)).deleteById(anyLong());



    }
}