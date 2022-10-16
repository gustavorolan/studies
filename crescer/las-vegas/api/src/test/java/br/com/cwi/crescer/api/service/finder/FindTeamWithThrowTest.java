package br.com.cwi.crescer.api.service.finder;

import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.repository.TeamRepository;
import br.com.cwi.crescer.api.util.TeamFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FindTeamWithThrowTest {
    @InjectMocks
    private FindTeamWithThrow findTeamWithThrow;

    @Mock
    private TeamRepository teamRepository;

    @Test
    @DisplayName("Should find team")
    void findTeamById() {
        Long id = 1L;

        Team team = TeamFactory.get();

        Mockito.when(teamRepository.findById(id))
                .thenReturn(Optional.of(team));

        Team value = findTeamWithThrow.findByIdWithException(id);

        Assertions.assertEquals(team, value);
    }

    @Test
    @DisplayName("Should find team with active parameter")
    void findTeamByIdAndActive() {
        Long id = 1L;
        Boolean active = true;

        Team team = TeamFactory.get();

        Mockito.when(teamRepository.findByIdAndActive(id, active))
                .thenReturn(Optional.of(team));

        Team value = findTeamWithThrow.findByIdAndActiveWithException(id, active);

        Assertions.assertEquals(team, value);
    }

    @Test
    @DisplayName("Should throw exception whent attempting to find team")
    void findTeamByIdWithThrow() {
        Long id = 1L;

        Mockito.when(teamRepository.findById(id))
                .thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            findTeamWithThrow.findByIdWithException(id);
        });

        Mockito.verify(teamRepository).findById(id);

        Assertions.assertEquals("404 NOT_FOUND \"Team does not exist\"", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception whent attempting to find team with active parameter")
    void findTeamByIdAndActiveWithThrow() {
        Long id = 1L;
        Boolean active = true;

        Mockito.when(teamRepository.findByIdAndActive(id, active))
                .thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            findTeamWithThrow.findByIdAndActiveWithException(id, active);
        });

        Mockito.verify(teamRepository).findByIdAndActive(id, active);

        Assertions.assertEquals("404 NOT_FOUND \"Team does not exist\"", exception.getMessage());
    }
}
