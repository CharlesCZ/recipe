package guru.springframework.repositories.reactive;

import guru.springframework.BootStrap.RecipeBootstrap;
import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryIT {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() throws Exception {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSave() {
        Category category=new Category();
        category.setDescription("Foo");

        categoryReactiveRepository.save(category).block();

        Long count=categoryReactiveRepository.count().block();

        assertEquals(Long.valueOf(1L),count);
    }

    @Test
    public void testFindByDescription() throws Exception {
        Category cat1 = new Category();
        cat1.setDescription("An");

        categoryReactiveRepository.save(cat1).block();

        Category fetchedCategory = categoryReactiveRepository.findByDescription("An").block();


        assertEquals("An", fetchedCategory.getDescription());
    }

}