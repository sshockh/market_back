package ru.sshock.market.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(
        name = "roles",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        }
)
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private ERole name;

    public Role(ERole name) {
        this.name = name;
    }

    public Role() {
    }
}
