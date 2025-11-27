package mk.ukim.finki.wp.lab1b.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Chef {
    private long id;
    private String firstName;
    private String lastName;
    private String bio;
    private List<Dish> dishes;
}
