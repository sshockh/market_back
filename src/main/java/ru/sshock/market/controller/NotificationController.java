package ru.sshock.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sshock.market.exception.AppException;
import ru.sshock.market.model.Notification;
import ru.sshock.market.repository.NotificationRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/service")
public class NotificationController {

    @Autowired
    private NotificationRepository repository;

    @GetMapping
    public ResponseEntity<List<Notification>> getAll() {
        return ResponseEntity.ok(repository.findByReadedFalse());
    }

    @PostMapping
    public ResponseEntity<Notification> save(@RequestBody Notification notification) {
        return ResponseEntity.ok(repository.save(new Notification(notification.getText())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notification> markAsRead(@PathVariable("id") @NotNull Long id) throws AppException {
        return repository.findById(id)
                .map(notification -> {
                    notification.setReaded(true);
                    return ResponseEntity.ok(repository.save(notification));
                })
                .orElseThrow(() -> new AppException("Запись id=" + id + "не найдена"));
    }
}
