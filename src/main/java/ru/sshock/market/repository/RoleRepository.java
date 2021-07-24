package ru.sshock.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sshock.market.model.ERole;
import ru.sshock.market.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);

    boolean existsByName(ERole name);
}
