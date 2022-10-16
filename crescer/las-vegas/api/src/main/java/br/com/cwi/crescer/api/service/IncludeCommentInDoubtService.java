package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.CommentRequest;
import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.mapper.CommentMapper;
import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.model.Comment;
import br.com.cwi.crescer.api.model.Doubt;
import br.com.cwi.crescer.api.repository.AttachmentRepository;
import br.com.cwi.crescer.api.repository.DoubtRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindDoubtWithThrow;
import br.com.cwi.crescer.api.validator.ParametersRegexValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class IncludeCommentInDoubtService {

    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private DoubtRepository doubtRepository;

    @Autowired
    private ParametersRegexValidator parametersRegexValidator;

    @Autowired
    private FindDoubtWithThrow findDoubtWithThrow;

    public ResponseMessage includeCommentInDoubt(CommentRequest request, Long doubtId, String imageId) {
        parametersRegexValidator.verifyIfImageIdIsValid(imageId);

        UserAccount userAccountLogado = userAccountAuthenticatedService.get();

        Comment comment = commentMapper.toEntity(request);
        if (!imageId.equals("undefined")) {
            Attachment attachment = attachmentRepository.findById(imageId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found."));
            comment.setImage(attachment);
        }

        Doubt doubt = findDoubtWithThrow.findByIdAndActiveWithException(doubtId, true);

        comment.setAuthor(userAccountLogado);
        comment.setDoubt(doubt);
        comment.setDateTimeCreation(LocalDateTime.now());
        comment.setIsRightResponse(false);
        comment.setActive(true);
        doubt.getComments().add(comment);
        comment.setDoubt(doubt);

        doubtRepository.save(doubt);

        return ResponseMessage.builder().response("You've Included successfully").build();

    }
}