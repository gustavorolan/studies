package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.response.AttachmentResponseData;
import br.com.cwi.crescer.api.model.Attachment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class AttachmentMapper {
    public AttachmentResponseData toResponse(MultipartFile file, Attachment attachment, String downloadURl) throws IOException {
        return AttachmentResponseData.builder()
                .id(attachment.getId())
                .fileName(attachment.getFileName())
                .downloadURL(downloadURl)
                .fileType(file.getContentType())
                .fileSize(file.getSize())
                .build();
    }

    public AttachmentResponseData toResponseAttachment(Attachment attachment) {
        return AttachmentResponseData.builder()
                .id(attachment.getId())
                .fileName(attachment.getFileName())
                .fileType(attachment.getFileType())
                .build();
    }
}