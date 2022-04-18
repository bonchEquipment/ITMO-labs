package project.repositories;

import project.entities.Click;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClickRepository extends JpaRepository<Click, Integer> {
    //ORM      object relational
    //JPA
}
