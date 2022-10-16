package br.com.cwi.crescer.api.service.finder;

import br.com.cwi.crescer.api.model.Doubt;
import br.com.cwi.crescer.api.repository.DoubtRepository;
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
public class FindDoubtWithThrowTest {
    @InjectMocks
    private FindDoubtWithThrow findDoubtWithThrow;

    @Mock
    private DoubtRepository doubtRepository;

    @Test
    @DisplayName("Should find doubt")
    void findDoubtById() {
        Long id = 1L;

        Doubt doubt = DoubtFactory.getDoubt();

        Mockito.when(doubtRepository.findById(id))
                .thenReturn(Optional.of(doubt));

        Doubt value = findDoubtWithThrow.findByIdWithException(id);

        Mockito.verify(doubtRepository).findById(id);

        Assertions.assertEquals(doubt, value);
    }

    @Test
    @DisplayName("Should find doubt with active parameter")
    void findDoubtByIdAndActive() {
        Long id = 1L;
        Boolean active = true;

        Doubt doubt = DoubtFactory.getDoubt();

        Mockito.when(doubtRepository.findByIdAndActive(id, active))
                .thenReturn(Optional.of(doubt));

        Doubt value = findDoubtWithThrow.findByIdAndActiveWithException(id, active);

        Mockito.verify(doubtRepository).findByIdAndActive(id, active);

        Assertions.assertEquals(doubt, value);
    }

    @Test
    @DisplayName("Should throw exception whent attempting to find doubt")
    void findDoubtByIdWithThrow() {
        Long id = 1L;

        Mockito.when(doubtRepository.findById(id))
                .thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            findDoubtWithThrow.findByIdWithException(id);
        });

        Mockito.verify(doubtRepository).findById(id);

        Assertions.assertEquals("404 NOT_FOUND \"Doubt does not exist\"", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception whent attempting to find doubt with active parameter")
    void findDoubtByIdAndActiveWithThrow() {
        Long id = 1L;
        Boolean active = true;

        Mockito.when(doubtRepository.findByIdAndActive(id, active))
                .thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            findDoubtWithThrow.findByIdAndActiveWithException(id, active);
        });

        Mockito.verify(doubtRepository).findByIdAndActive(id, active);

        Assertions.assertEquals("404 NOT_FOUND \"Doubt does not exist\"", exception.getMessage());
    }

    @Test
    @DisplayName("Should find doubt by active parameter and order by date time creation")
    void findByActiveOrderByDateTimeCreation() {
        Pageable pageable = Pageable.ofSize(1).withPage(0);
        Boolean active = true;

        Doubt doubtOlder = DoubtFactory.getDoubt();
        Doubt doubt = DoubtFactory.getDoubt();

        Page<Doubt> doubts = new PageImpl<Doubt>(List.of(doubt, doubtOlder));

        Mockito.when(doubtRepository.findByActiveOrderByDateTimeCreationDesc(active, pageable))
                .thenReturn(Optional.of(doubts));

        Page<Doubt> value = findDoubtWithThrow.findByActiveOrderByDateTimeCreationWithException(active, pageable);

        Mockito.verify(doubtRepository).findByActiveOrderByDateTimeCreationDesc(active, pageable);

        Assertions.assertEquals(doubts, value);
    }

    @Test
    @DisplayName("Should throw error when attempting to find doubt by active parameter and order by date time creation")
    void findByActiveOrderByDateTimeCreationWitError() {
        Pageable pageable = Pageable.ofSize(1).withPage(0);
        Boolean active = true;

        Mockito.when(doubtRepository.findByActiveOrderByDateTimeCreationDesc(active, pageable))
                .thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            findDoubtWithThrow.findByActiveOrderByDateTimeCreationWithException(active, pageable);
        });

        Mockito.verify(doubtRepository).findByActiveOrderByDateTimeCreationDesc(active, pageable);

        Assertions.assertEquals("404 NOT_FOUND \"Doubt does not exist\"", exception.getMessage());
    }
}