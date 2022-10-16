package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.CommentResponse;
import br.com.cwi.crescer.api.mapper.CommentMapper;
import br.com.cwi.crescer.api.model.Doubt;
import br.com.cwi.crescer.api.service.finder.FindCommentWithThrow;
import br.com.cwi.crescer.api.service.finder.FindDoubtWithThrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetAllByDoubtService {
    @Autowired
    private FindDoubtWithThrow findDoubtWithThrow;

    @Autowired
    private FindCommentWithThrow findCommentWithThrow;

    @Autowired
    private CommentMapper commentMapper;

    public Page<CommentResponse> getAllByDoubt(Pageable pageable, Long doubtId) {
        Doubt doubt = findDoubtWithThrow.findByIdAndActiveWithException(doubtId, true);

        Page<CommentResponse> response = findCommentWithThrow.findByDoubtAndActiveWithException(pageable, doubt, true)
                .map(comment -> commentMapper.toResponse(comment));

        return response;
    }
}
