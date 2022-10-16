package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.DoubtResponse;
import br.com.cwi.crescer.api.mapper.DoubtMapper;
import br.com.cwi.crescer.api.service.finder.FindDoubtWithThrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class GetAllDoubtsService {

    @Autowired
    private DoubtMapper doubtMapper;

    @Autowired
    private FindDoubtWithThrow findDoubtWithThrow;

    public static final int NUMBER_OF_DOUBTS_PER_PAGE = 5;

    public Page<DoubtResponse> getAllDoubts(int page) {

        Pageable pageable = PageRequest.of(page, NUMBER_OF_DOUBTS_PER_PAGE);

        return findDoubtWithThrow.findByActiveOrderByDateTimeCreationWithException(true, pageable)
                .map(doubt -> doubtMapper.toResponse(doubt));
    }
}