package mk.ukim.finki.wp.lab1b.repository;

import mk.ukim.finki.wp.lab1b.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab1b.model.Chef;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryChefRepository implements ChefRepository{
    @Override
    public List<Chef> findAll() {
        return DataHolder.chefs;
    }

    @Override
    public Optional<Chef> findById(Long id) {
        return DataHolder.chefs.stream().filter(chef -> chef.getId() == id).findFirst();
    }

    @Override
    public Chef save(Chef chef) {
        DataHolder.chefs.removeIf(chef1 -> chef1.getFirstName().equals(chef.getFirstName()) && chef1.getLastName().equals(chef.getLastName()));
        DataHolder.chefs.add(chef);
        return chef;
    }
}
