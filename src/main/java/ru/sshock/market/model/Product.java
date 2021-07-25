package ru.sshock.market.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        name = "products",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        }
)
@Builder
@Getter
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    private String desc;
    @NotNull
    @ManyToOne
    private Category category;

    private Double price;

    private String image;

    private Integer availableCount;

    public Product() {
    }

}
