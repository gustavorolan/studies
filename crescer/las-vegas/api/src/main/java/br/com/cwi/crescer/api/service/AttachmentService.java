package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.AttachmentResponseData;
import br.com.cwi.crescer.api.mapper.AttachmentMapper;
import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.repository.AttachmentRepository;
import br.com.cwi.crescer.api.service.cloud.ImageValidate;
import br.com.cwi.crescer.api.service.finder.FindAttachmentWithThrow;
import br.com.cwi.crescer.api.validator.MagicNumberValidator;
import br.com.cwi.crescer.api.validator.ParametersRegexValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
public class AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Autowired
    private ParametersRegexValidator parametersRegexValidator;

    @Autowired
    private MagicNumberValidator magicNumberValidator;

    @Autowired
    private  ImageValidate imageValidate;

    @Autowired
    private FindAttachmentWithThrow findAttachmentWithThrow;

    public Attachment saveAttachment(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            parametersRegexValidator.validateAttachmentFileName(fileName);
            parametersRegexValidator.validateAttachmentContentType(file.getContentType());
            magicNumberValidator.validateAttachment(file);

            Attachment attachment
                    = new Attachment(fileName,
                    file.getContentType(),
                    file.getBytes());

            return attachmentRepository.save(attachment);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not save File: " + fileName);
        }
    }

    public Attachment getAttachment(String fileId) {
        return attachmentRepository
                .findById(fileId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found"));
    }


    public AttachmentResponseData uploadFile(MultipartFile file) throws IOException {

//        imageValidate.imageValidator(file.getBytes());

        Attachment attachment = this.saveAttachment(file);

        return attachmentMapper.toResponseAttachment(attachment);
    }

    public ResponseEntity<Resource> download(String id) {
        Attachment attachment = findAttachmentWithThrow.findByIdWithException(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment")
                .body(new ByteArrayResource(attachment.getData()));
    }
}