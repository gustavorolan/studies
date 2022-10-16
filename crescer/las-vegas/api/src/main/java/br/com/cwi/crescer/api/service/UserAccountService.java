package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.RegisterUserRequest;
import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.mapper.UserMapper;
import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.model.Permission;
import br.com.cwi.crescer.api.security.repository.UserAccountRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.service.cloud.ImageValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static br.com.cwi.crescer.api.model.InitialScore.SCORE;

@Service
public class UserAccountService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private ImageValidate imageValidate;

    public ResponseMessage include(RegisterUserRequest request, MultipartFile file) throws IOException {
        imageValidate.imageValidator(file.getBytes());
        Attachment attachment = attachmentService.saveAttachment(file);


        UserAccount userAccount = userMapper.toEntity(request);
        userAccount.setAssessment(SCORE.getScore());
        userAccount.setPermission(Permission.NORMAL);
        userAccount.setActive(true);
        userAccount.setProfileImage(attachment);
        userAccountRepository.save(userAccount);

        return ResponseMessage.builder().response("User created successfully").build();
    }
}