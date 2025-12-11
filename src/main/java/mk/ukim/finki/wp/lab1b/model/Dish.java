package mk.ukim.finki.wp.lab1b.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dishId;
    private String name;
    private String cuisine;
    private int preparationTime;
    @ManyToOne
    private Chef chef;

    public Dish(long l, String number, String cordonBleu, String french, int i) {
    }
}
