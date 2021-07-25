package ru.sshock.market.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import ru.sshock.market.model.*;
import ru.sshock.market.repository.CategoryRepository;
import ru.sshock.market.repository.ProductRepository;
import ru.sshock.market.repository.RoleRepository;
import ru.sshock.market.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Component
public class AppInitializator {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ResourceLoader resourceLoader;

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

    private void createProducts() throws IOException {
        // считываем категории
        Map<String, Category> categories = new HashMap<>();
        for (Category category : categoryRepository.findAll()) {
            categories.put(category.getName(), category);
        }

        // TODO: сделать загрузку из файла products.json
        //Resource resource = resourceLoader.getResource("classpath:product.json");
        //resource.getFile()

        List<Product> products = new ArrayList<>();
        products.add(
                Product.builder()
                        .name("Лодка ПВХ")
                        .desc("Великолепный вариант для семейного отдыха и рыбалки. Выбор истинного <strike>лоха</strike> рыбака")
                        .price(4000.0)
                        .availableCount(99)
                        .image("lodka_pvh.jpg")
                        .category(categories.get("Спорт и отдых"))
                        .build()
        );
        products.add(
                Product.builder()
                        .name("Лодка ПВХ Ривьера 3400 СК (КОМПАКТ) надувная под мотор")
                        .desc("Лодка Ривьера 3400 СК версии компакт/лайт/light - это прекрасный пример качественной лодки по адекватной цене с отличными техническими характеристиками")
                        .price(39170.0)
                        .availableCount(7)
                        .image("river-3400-1.jpg")
                        .category(categories.get("Спорт и отдых"))
                        .build()
        );
        products.add(
                Product.builder()
                        .name("Лодка ПВХ DRAGON 330 Classic PRO 9мм сборная слань на стрингерах")
                        .desc("Лодка ПВХ Dragon 330 Classic PRO - надежная моторная лодка из армированной ткани плотностью 1100 г/м2 с классическими баллонами. В базовой комплекатации идут мягкие накладки на сиденья")
                        .price(31990.0)
                        .availableCount(5)
                        .image("dragon-330-classic-base-1.jpg")
                        .category(categories.get("Спорт и отдых"))
                        .build()
        );
        products.add(
                Product.builder()
                        .name("Лодка ПВХ Аква 2800 надувная под мотор")
                        .desc("Надувная лодка АКВА 2800 имеет положительные отзывы владельцев благодаря хорошим соотношениям цены и характеристик. Кокпит: 208x62 см. По периметру установлен привальный брус с волноотбойником")
                        .price(16520.0)
                        .availableCount(3)
                        .image("1698_2.jpg")
                        .category(categories.get("Спорт и отдых"))
                        .build()
        );
        products.add(
                Product.builder()
                        .name("Фирменная футболка ZoMarket")
                        .desc("Zoooooooo")
                        .price(2999.90)
                        .availableCount(997)
                        .image("zo.jpg")
                        .category(categories.get("Одежда"))
                        .build()
        );
        productRepository.saveAll(products);
    }
}
