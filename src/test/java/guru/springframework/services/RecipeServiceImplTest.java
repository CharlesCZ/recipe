package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {


    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        recipeService= new RecipeServiceImpl(recipeRepository);
    }



    @Test
    public void getRecipeByIdTest() throws  Exception{
        Recipe recipe=new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional=Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned=recipeService.findById(1L);
        assertNotNull("Null recipe returned",recipeReturned);

        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();
    }

@Test
public void getRecipesTest() throws Exception{

Recipe recipe=new Recipe();
    HashSet receipesData = new HashSet();
    receipesData.add(recipe);

    when(recipeService.getRecipes()).thenReturn(receipesData);

    Set<Recipe> recipes=recipeService.getRecipes();
    assertEquals(1L,recipes.size());
    verify(recipeRepository,times(1)).findAll();
    verify(recipeRepository,never()).findById(anyLong());


}

}