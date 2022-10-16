package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.CommentResponse;
import br.com.cwi.crescer.api.mapper.CommentMapper;
import br.com.cwi.crescer.api.model.Comment;
import br.com.cwi.crescer.api.model.Doubt;
import br.com.cwi.crescer.api.service.finder.FindCommentWithThrow;
import br.com.cwi.crescer.api.service.finder.FindDoubtWithThrow;
import br.com.cwi.crescer.api.util.CommentFactory;
import br.com.cwi.crescer.api.util.DoubtFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GetAllByDoubtServiceTest {

    @InjectMocks
    private GetAllByDoubtService getAllByDoubtService;

    @Mock
    private FindDoubtWithThrow findDoubtWithThrow;

    @Mock
    private FindCommentWithThrow findCommentWithThrow;

    @Mock
    private CommentMapper commentMapper;

    @Test
    @DisplayName("Should get all videos evaluations with desc ordenation and filter and score parameters")
    void getAllDoubtComments() {
        Long doubtId = 1L;
        Pageable pageable = Pageable.ofSize(1).withPage(0);
        Boolean active = true;

        Doubt doubt = DoubtFactory.getDoubt();

        Mockito.when(findDoubtWithThrow.findByIdAndActiveWithException(doubtId,  active))
                .thenReturn(doubt);

        Comment comment = CommentFactory.get();
        CommentResponse commentResponse = CommentFactory.getBuilderCommentResponse().build();

        Page<Comment> response = new PageImpl<Comment>(List.of(comment));

        Page<CommentResponse> pageResponse = new PageImpl<CommentResponse>(List.of(commentResponse));

        Mockito.when(findCommentWithThrow.findByDoubtAndActiveWithException(pageable, doubt, active))
                .thenReturn(response);

        Mockito.when(commentMapper.toResponse(comment))
                .thenReturn(commentResponse);

        Page<CommentResponse> value = getAllByDoubtService.getAllByDoubt(pageable, doubtId);

        Mockito.verify(findDoubtWithThrow).findByIdAndActiveWithException(doubtId,  active);
        Mockito.verify(findCommentWithThrow).findByDoubtAndActiveWithException(pageable, doubt, active);
        Mockito.verify(findCommentWithThrow).findByDoubtAndActiveWithException(pageable, doubt, active);
        Mockito.verify(commentMapper).toResponse(comment);

        Assertions.assertEquals(pageResponse, value);

    }
}
