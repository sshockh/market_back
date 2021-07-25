package ru.sshock.market.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class AppSettings {

    @Getter
    @Setter
    private int jwtExpirationMs = 7_200_000;
}
