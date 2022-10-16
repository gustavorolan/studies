package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.service.finder.FindAttachmentWithThrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyImageParameterService {

    @Autowired
    private FindAttachmentWithThrow findAttachmentWithThrow;

    public Attachment verifyImageParameter(String imageId) {
        return imageId.isBlank() ? null : findAttachmentWithThrow.findByIdWithException(imageId);
    }
}
