package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository  extends JpaRepository<Notification, Long> {
}
