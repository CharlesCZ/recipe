package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryIT {

    public static final String EACH = "Each";
    @Autowired
    UnitOfMeasureReactiveRepository reactiveRepository;

    @Before
    public void setUp() throws Exception {
        reactiveRepository.deleteAll().block();
    }

    @Test
    public void testSaveUom() {
        UnitOfMeasure uom=new UnitOfMeasure();
        uom.setDescription(EACH);

        reactiveRepository.save(uom).block();

        Long count=reactiveRepository.count().block();

        assertEquals(Long.valueOf(1L),count);
    }


    @Test
    public void testFindByDescription() {
        UnitOfMeasure uom=new UnitOfMeasure();
        uom.setDescription(EACH);
        reactiveRepository.save(uom).block();

       UnitOfMeasure fetchedUom= reactiveRepository.findByDescription(EACH).block();

        assertEquals(EACH,fetchedUom.getDescription());
    }
}