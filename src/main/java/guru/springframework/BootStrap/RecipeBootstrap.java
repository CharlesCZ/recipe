package guru.springframework.BootStrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
    public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

        private final RecipeRepository recipeRepository;
        private final CategoryRepository categoryRepository;
        private final UnitOfMeasureRepository unitOfMeasureRepository;


    public RecipeBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
        public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading Bootstrap Data");

    }
    private List<Recipe> getRecipes(){
        List<Recipe> recipes =new ArrayList<>();
        //get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");


        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if(!dashUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if(!pintUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if(!cupsUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

Optional<Category> americanCategoryOptional=categoryRepository.findByDescription("American");

        if(!americanCategoryOptional.isPresent()){

            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> mexicanCategoryOptional=categoryRepository.findByDescription("Mexican");

        if(!mexicanCategoryOptional.isPresent()){

            throw new RuntimeException("Expected Category Not Found");
        }
Category americanCategory=americanCategoryOptional.get();
Category mexicanCategory=mexicanCategoryOptional.get();
//Guacamole
            Recipe recipeGuacamole=new Recipe();
            recipeGuacamole.setPrepTime(10);
            recipeGuacamole.setCookTime(0);
            recipeGuacamole.setDescription("How to Make Perfect Guacamole Recipe");
            recipeGuacamole.setDifficulty(Difficulty.EASY);
            recipeGuacamole.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
        "\n" + "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
        "\n" + "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
        "\n" + "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" + "\n" + "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
        "\n" + "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
        "\n" + "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");
        recipeGuacamole.addIngredient(new Ingredient("ripe avocados",new BigDecimal(2), unitOfMeasureRepository.findByDescription("Each").get()));
        recipeGuacamole.addIngredient(new Ingredient("Kosher salt",new BigDecimal("0.5"),unitOfMeasureRepository.findByDescription("Teaspoon").get()));
        recipeGuacamole.addIngredient(new Ingredient("fresh lime juice or lemon juice",new BigDecimal(1),unitOfMeasureRepository.findByDescription("Tablespoon").get()));
        recipeGuacamole.addIngredient(new Ingredient("minced red onion or thinly sliced green onion",new BigDecimal("0.25"),unitOfMeasureRepository.findByDescription("Cup").get()));
        recipeGuacamole.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced",new BigDecimal(2),unitOfMeasureRepository.findByDescription("Each").get()));
        recipeGuacamole.addIngredient(new Ingredient("Cilantro (leaves and tender stems), finely chopped",new BigDecimal(2),unitOfMeasureRepository.findByDescription("Tablespoon").get()));
        recipeGuacamole.addIngredient(new Ingredient(" A dash of freshly grated black pepper",new BigDecimal(1),unitOfMeasureRepository.findByDescription("Dash").get()));
        recipeGuacamole.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal("0.5"), unitOfMeasureRepository.findByDescription("Each").get()));





            Notes recipeNotes=new Notes();

            recipeNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                    "\n" + "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                    "\n" + "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                    "\n" + "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                    "\n" + "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!");
recipeGuacamole.setNotes(recipeNotes);
recipeGuacamole.getCategories().add(mexicanCategory);
recipeGuacamole.getCategories().add(americanCategory);


recipeGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
recipes.add(recipeGuacamole);
//Taco
        Recipe recipeTaco=new Recipe();
        recipeTaco.setPrepTime(20);
        recipeTaco.setCookTime(15);
        recipeTaco.setDescription("Spicy Grilled Chicken Tacos");
recipeTaco.getCategories().add(mexicanCategory);
        recipeTaco.getCategories().add(americanCategory);
recipeTaco.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
Notes tacoNotes=new Notes();
tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla."+"\n" + "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
        "\n" + "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!" + "\n" + "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
        "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
        "Spicy Grilled Chicken TacosThe ancho chiles I use in the marinade are named for their wide shape. They are large, have a deep reddish brown color when dried, and are mild in flavor with just a hint of heat. You can find ancho chile powder at any markets that sell Mexican ingredients, or online.\n" +
        "I like to put all the toppings in little bowls on a big platter at the center of the table: avocados, radishes, tomatoes, red onions, wedges of lime, and a sour cream sauce. I add arugula, as well – this green isn’t traditional for tacos, but we always seem to have some in the fridge and I think it adds a nice green crunch to the tacos.\n" +
        "Everyone can grab a warm tortilla from the pile and make their own tacos just they way they like them.\n" + "You could also easily double or even triple this recipe for a larger party. A taco and a cold beer on a warm day? Now that’s living!");

recipeTaco.setNotes(tacoNotes);
recipeTaco.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" + "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
        "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" + "Spicy Grilled Chicken Tacos\n" + "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
        "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
        "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" + "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");
        recipeTaco.setDifficulty(Difficulty.EASY);


        recipeTaco.addIngredient(new Ingredient("ancho chili powder",new BigDecimal("2"),unitOfMeasureRepository.findByDescription("Tablespoon").get()));
        recipeTaco.addIngredient(new Ingredient("dried oregano",new BigDecimal("1"),unitOfMeasureRepository.findByDescription("Teaspoon").get()));
        recipeTaco.addIngredient(new Ingredient("dried cumin",new BigDecimal("1"),unitOfMeasureRepository.findByDescription("Teaspoon").get()));
        recipeTaco.addIngredient(new Ingredient("sugar",new BigDecimal("1"),unitOfMeasureRepository.findByDescription("Teaspoon").get()));
        recipeTaco.addIngredient(new Ingredient("salt",new BigDecimal("0.5"),unitOfMeasureRepository.findByDescription("Teaspoon").get()));
        recipeTaco.addIngredient(new Ingredient("garlic, finely chopped",new BigDecimal("1"),unitOfMeasureRepository.findByDescription("Each").get()));
        recipeTaco.addIngredient(new Ingredient("finely grated orange zest",new BigDecimal("1"),unitOfMeasureRepository.findByDescription("Tablespoon").get()));
        recipeTaco.addIngredient(new Ingredient("fresh-squeezed orange juice",new BigDecimal("3"),unitOfMeasureRepository.findByDescription("Tablespoon").get()));
        recipeTaco.addIngredient(new Ingredient("olive oil",new BigDecimal("6"),unitOfMeasureRepository.findByDescription("Tablespoon").get()));
        recipeTaco.addIngredient(new Ingredient("skinless, boneless chicken thighs ",new BigDecimal("6"),unitOfMeasureRepository.findByDescription("Each").get()));

recipes.add(recipeTaco);



        return recipes;
        }

    }

