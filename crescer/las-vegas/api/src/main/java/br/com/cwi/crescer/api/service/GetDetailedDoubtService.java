package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.DoubtResponse;
import br.com.cwi.crescer.api.mapper.DoubtMapper;
import br.com.cwi.crescer.api.model.Doubt;
import br.com.cwi.crescer.api.service.finder.FindDoubtWithThrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetDetailedDoubtService {

    @Autowired
    private DoubtMapper doubtMapper;

    @Autowired
    private FindDoubtWithThrow findDoubtWithThrow;

    public DoubtResponse getDetailedDoubt(Long idDoubt){
        Doubt doubt = findDoubtWithThrow.findByIdAndActiveWithException(idDoubt, true);

        return doubtMapper.toResponse(doubt);
    }
}