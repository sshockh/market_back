package ru.sshock.market.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sshock.market.model.Category;
import ru.sshock.market.model.ERole;
import ru.sshock.market.model.Role;
import ru.sshock.market.model.User;
import ru.sshock.market.repository.CategoryRepository;
import ru.sshock.market.repository.RoleRepository;
import ru.sshock.market.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Component
public class AppInitializator {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    CategoryRepository categoryRepository;

    /**
     * Загрузка первичных данных магазина
     * TODO: переделать на загрузку из json-файла
     */
    @PostConstruct
    private void init() throws IOException {

        if (userRepository.existsByUsername("admin")) return;

        createUsers();          // создание ролей и пользователей
        createCategories();     // создание категорий товаров
        createProducts();       // создание товаров
    }

    private void createUsers() {
        Role roleAdmin = roleRepository.save(new Role(ERole.ROLE_ADMIN));
        Role roleUser = roleRepository.save(new Role(ERole.ROLE_USER));

        User admin = new User("admin", "admin@mail.ru", "1235");
        admin.setRoles(new HashSet<>(Collections.singletonList(roleAdmin)));

        User tsar = new User("tsar", "tsar@mail.ru", "123");
        tsar.setRoles(new HashSet<>(Collections.singletonList(roleUser)));

        userRepository.saveAll(Arrays.asList(admin, tsar));
    }

    private void createCategories() {
        Arrays.asList("Одежда", "Дом и сад", "Детские товары", "Бытовая техника", "Спорт и отдых", "Строительство и ремонт", "Товары для животных")
                .forEach(name -> categoryRepository.save(new Category(name)));
    }

    private void createProducts() {

    }

}
