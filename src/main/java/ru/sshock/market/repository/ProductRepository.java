package ru.sshock.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sshock.market.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
