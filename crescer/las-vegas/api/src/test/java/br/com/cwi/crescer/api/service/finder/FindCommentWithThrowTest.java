package br.com.cwi.crescer.api.service.finder;

import br.com.cwi.crescer.api.model.Comment;
import br.com.cwi.crescer.api.model.Doubt;
import br.com.cwi.crescer.api.repository.CommentRepository;
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
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FindCommentWithThrowTest {
    @InjectMocks
    private FindCommentWithThrow findCommentWithThrow;

    @Mock
    private CommentRepository commentRepository;

    @Test
    @DisplayName("Should find comment")
    void findCommentById() {
        Long id = 1L;

        Comment comment = CommentFactory.get();

        Mockito.when(commentRepository.findById(id))
                .thenReturn(Optional.of(comment));

        Comment value = findCommentWithThrow.findByIdWithException(id);

        Assertions.assertEquals(comment, value);
    }

    @Test
    @DisplayName("Should find comment with active parameter")
    void findCommentByIdAndActive() {
        Long id = 1L;
        Boolean active = true;

        Comment comment = CommentFactory.get();

        Mockito.when(commentRepository.findByIdAndActive(id, active))
                .thenReturn(Optional.of(comment));

        Comment value = findCommentWithThrow.findByIdAndActiveWithException(id, active);

        Assertions.assertEquals(comment, value);
    }

    @Test
    @DisplayName("Should throw exception whent attempting to find comment")
    void findCommentByIdWithThrow() {
        Long id = 1L;

        Mockito.when(commentRepository.findById(id))
                .thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            findCommentWithThrow.findByIdWithException(id);
        });

        Mockito.verify(commentRepository).findById(id);

        Assertions.assertEquals("404 NOT_FOUND \"Comment does not exist\"", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception whent attempting to find comment with active parameter")
    void findCommentByIdAndActiveWithThrow() {
        Long id = 1L;
        Boolean active = true;

        Mockito.when(commentRepository.findByIdAndActive(id, active))
                .thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            findCommentWithThrow.findByIdAndActiveWithException(id, active);
        });

        Mockito.verify(commentRepository).findByIdAndActive(id, active);

        Assertions.assertEquals("404 NOT_FOUND \"Comment does not exist\"", exception.getMessage());
    }

    @Test
    @DisplayName("Should find comment by doubt and active parameter")
    void findByIdContainsNot() {
        Pageable pageable = Pageable.ofSize(1).withPage(0);
        Boolean active = true;

        Doubt doubt = DoubtFactory.getDoubt();
        Comment comment = CommentFactory.get();

        Page<Comment> comments = new PageImpl<Comment>(List.of(comment));

        Mockito.when(commentRepository.findByDoubtAndActive(doubt, active, pageable))
                .thenReturn(Optional.of(comments));

        Page<Comment> commentsResponse = findCommentWithThrow.findByDoubtAndActiveWithException(pageable, doubt, active);

        Mockito.verify(commentRepository).findByDoubtAndActive(doubt, active, pageable);

        Assertions.assertEquals(comments, commentsResponse);
    }

    @Test
    @DisplayName("Should find comment by doubt and active parameter")
    void findByIdContainsNotWithError() {
        Pageable pageable = Pageable.ofSize(1).withPage(0);
        Boolean active = true;

        Doubt doubt = DoubtFactory.getDoubt();

        Mockito.when(commentRepository.findByDoubtAndActive(doubt, active, pageable))
                .thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            findCommentWithThrow.findByDoubtAndActiveWithException(pageable, doubt, active);
        });

        Mockito.verify(commentRepository).findByDoubtAndActive(doubt, active, pageable);

        Assertions.assertEquals("404 NOT_FOUND \"Comment does not exist\"", exception.getMessage());
    }
}