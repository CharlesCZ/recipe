package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String DESCRIPTION = "description";

    public CategoryToCategoryCommand converter;

@Before
    public void setUp(){
converter=new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject() {

    assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
    assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convert() {
        //given
Category source =new Category();
source.setId(ID_VALUE);
source.setDescription(DESCRIPTION);

//when
        CategoryCommand categoryCommand = converter.convert(source);


//then
assertEquals(ID_VALUE,categoryCommand.getId());
assertEquals(DESCRIPTION,categoryCommand.getDescription());

    }
}