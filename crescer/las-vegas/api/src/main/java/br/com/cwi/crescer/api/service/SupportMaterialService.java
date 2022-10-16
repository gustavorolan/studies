package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.repository.VideoRepository;
import br.com.cwi.crescer.api.service.finder.FindVideoWithThrow;
import br.com.cwi.crescer.api.validator.IsUserLoggedValidator;
import br.com.cwi.crescer.api.validator.ParametersRegexValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
public class SupportMaterialService {
    @Autowired
    private ParametersRegexValidator parametersRegexValidator;

    @Autowired
    private FindVideoWithThrow findVideoWithThrow;

    @Autowired
    private IsUserLoggedValidator isUserLoggedValidator;

    @Autowired
    private VideoRepository repository;

    public ResponseMessage uploadSupportMaterial(MultipartFile supportMaterial, Long videoId) {
        try{
        Video video = findVideoWithThrow.findByIdWithException(videoId);
//        supportMaterialAlreadyExistsValidator.supportMaterialAlreadyExists(video.getSupportMaterial());
        isUserLoggedValidator.verify(video.getAuthor());
        parametersRegexValidator.validatePdfContentType(supportMaterial.getContentType());
        parametersRegexValidator.validatePdfFileName(supportMaterial.getOriginalFilename());
        video.setSupportMaterial(supportMaterial.getBytes());
        repository.save(video);
        return  ResponseMessage.builder().response("You've added with success").build();
        }catch (IOException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Not capable to convert");
        }
    }

}
