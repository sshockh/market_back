package ru.sshock.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sshock.market.model.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    boolean existsByName(String name);
}
