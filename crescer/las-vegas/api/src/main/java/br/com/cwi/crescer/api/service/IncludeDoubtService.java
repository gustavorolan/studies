package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.DoubtRequest;
import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.mapper.DoubtMapper;
import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.model.Doubt;
import br.com.cwi.crescer.api.repository.AttachmentRepository;
import br.com.cwi.crescer.api.repository.DoubtRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.validator.ParametersRegexValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class IncludeDoubtService {

    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private DoubtMapper doubtMapper;

    @Autowired
    private DoubtRepository doubtRepository;

    @Autowired
    private ParametersRegexValidator parametersRegexValidator;


    public ResponseMessage includeDoubt(DoubtRequest request, String imageId) {
        parametersRegexValidator.verifyIfImageIdIsValid(imageId);

        UserAccount userAccountLogado = userAccountAuthenticatedService.get();

        Doubt doubt = doubtMapper.toEntity(request);
        if (!imageId.equals("undefined")) {
            Attachment attachment = attachmentRepository.findById(imageId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found."));
            doubt.setImage(attachment);
        }

        doubt.setAuthor(userAccountLogado);
        doubt.setDateTimeCreation(LocalDateTime.now());
        doubt.setActive(true);
        doubt.setFinished(false);

        doubtRepository.save(doubt);

        return ResponseMessage.builder().response("You've included successfully").build();

    }
}
