package br.com.cwi.crescer.api.service.finder;

import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class FindAttachmentWithThrow {

    @Autowired
    private AttachmentRepository attachmentRepository;

    private static final String RESPONSE = "Attachment does not exist";

    public Attachment findByIdWithException(String id) {
        return attachmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }
}
