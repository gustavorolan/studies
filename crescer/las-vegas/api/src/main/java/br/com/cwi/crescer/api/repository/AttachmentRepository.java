package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, String> {
    Optional<Attachment> findByFileName(String fileName);
}