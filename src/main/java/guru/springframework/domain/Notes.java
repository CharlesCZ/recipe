package guru.springframework.domain;

import lombok.*;
import org.springframework.data.annotation.Id;



/**
 * Created by jt on 6/13/17.
 */


@Getter
@Setter
public class Notes {


    private String id;
    private Recipe recipe;
    private String recipeNotes;

}
