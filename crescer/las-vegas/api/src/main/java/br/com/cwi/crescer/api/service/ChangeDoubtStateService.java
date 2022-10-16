package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Doubt;
import br.com.cwi.crescer.api.repository.DoubtRepository;
import br.com.cwi.crescer.api.service.finder.FindDoubtWithThrow;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangeDoubtStateService {

    @Autowired
    private DoubtRepository doubtRepository;

    @Autowired
    private FindDoubtWithThrow findDoubtWithThrow;

    @Autowired
    private PermissionValidator permissionValidator;

    public ResponseMessage changeDoubtState(Long doubtId) {
        Doubt doubt = findDoubtWithThrow.findByIdAndActiveWithException(doubtId, true);

        permissionValidator.validateLoggedUserPermission(doubt.getAuthor());

        Boolean finished = doubt.getFinished();

        doubt.setFinished(!finished);

        doubtRepository.save(doubt);

        return ResponseMessage.builder().response("You've changed successfully").build();
    }}