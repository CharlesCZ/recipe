package guru.springframework.domain;

import lombok.*;
import org.springframework.data.annotation.Id;


import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */

@Getter
@Setter
public class Category {

    private String id;
    private String description;
    private Set<Recipe> recipes;
}
