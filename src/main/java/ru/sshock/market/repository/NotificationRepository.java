package ru.sshock.market.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sshock.market.model.Notification;

import java.util.List;

public interface NotificationRepository extends CrudRepository<Notification, Long> {

    List<Notification> findByReadedFalse();
}
