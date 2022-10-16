package br.com.cwi.crescer.api.service.finder;

import br.com.cwi.crescer.api.model.Comment;
import br.com.cwi.crescer.api.model.Doubt;
import br.com.cwi.crescer.api.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class FindCommentWithThrow {
    @Autowired
    private CommentRepository commentRepository;

    private static final String RESPONSE = "Comment does not exist";

    public Comment findByIdAndActiveWithException(Long id, Boolean active) {
        return commentRepository.findByIdAndActive(id, active)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }

    public Comment findByIdWithException(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }

    public Page<Comment> findByDoubtAndActiveWithException(Pageable pageable, Doubt doubt, Boolean active) {
        return commentRepository.findByDoubtAndActive(doubt, active, pageable)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }
}
