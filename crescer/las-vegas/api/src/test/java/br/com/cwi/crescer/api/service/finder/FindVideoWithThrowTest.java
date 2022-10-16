package br.com.cwi.crescer.api.service.finder;

import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.model.VideoType;
import br.com.cwi.crescer.api.repository.VideoRepository;
import br.com.cwi.crescer.api.util.VideoFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class FindVideoWithThrowTest {

    @InjectMocks
    private  FindVideoWithThrow findVideoWithThrow;

    @Mock
    private VideoRepository videoRepository;


    @Test
    @DisplayName("Should find video by id")
    void findById() {
        Long id = 1L;

        Video video = VideoFactory.get();

        Mockito.when(videoRepository.findById(id)).thenReturn(Optional.ofNullable(video));

        Video value = findVideoWithThrow.findByIdWithException(id);

        Mockito.verify(videoRepository).findById(id);

        Assertions.assertEquals(video, value);
    }

    @Test
    @DisplayName("Should throw an exception, because video was not found")
    void findByIdWithException() {
        Long id = 1L;

        Mockito.when(videoRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            findVideoWithThrow.findByIdWithException(id);
        });

        Mockito.verify(videoRepository).findById(id);

        Assertions.assertEquals("404 NOT_FOUND \"Video does not exist\"", exception.getMessage());
    }

    @Test
    @DisplayName("Should find video by id and active parameter")
    void findByIdAndActive() {
        Long id = 1L;

        Video video = VideoFactory.get();

        Mockito.when(videoRepository.findById(id)).thenReturn(Optional.ofNullable(video));

        Video value = findVideoWithThrow.findByIdWithException(id);

        Mockito.verify(videoRepository).findById(id);

        Assertions.assertEquals(video, value);
    }

    @Test
    @DisplayName("Should find video by id, type and active parameter")
    void findByActiveAndType() {
        Long id = 1L;
        Boolean active = true;

        Video video = VideoFactory.get();

        Mockito.when(videoRepository.findByIdAndActive(id, active))
                .thenReturn(Optional.ofNullable(video));

        Video value = findVideoWithThrow.findByIdAndActiveWithException(id, active);

        Mockito.verify(videoRepository).findByIdAndActive(id, active);

        Assertions.assertEquals(video, value);
    }

    @Test
    @DisplayName("Should find video by id, type and active parameter")
    void findByActiveAndTypeWithError() {
        Long id = 1L;
        Boolean active = true;
        
        Mockito.when(videoRepository.findByIdAndActive(id, active))
                .thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            findVideoWithThrow.findByIdAndActiveWithException(id, active);
        });

        Mockito.verify(videoRepository).findByIdAndActive(id, active);

        Assertions.assertEquals("404 NOT_FOUND \"Video does not exist\"", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw error when attempting to find video by id and active parameter")
    void findByActiveAndTypeTWithError() {
        String filter = "";
        Boolean active = true;
        VideoType type = VideoType.COURSE;
        Pageable pageable = Pageable.ofSize(1).withPage(0);

        Mockito.when(videoRepository.findByAndActiveAndTypeAndNameContains(active, type, filter, pageable))
                .thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            findVideoWithThrow.findByActiveAndTypeTWithException(active, pageable, type, filter);
        });

        Mockito.verify(videoRepository).findByAndActiveAndTypeAndNameContains(active, type, filter, pageable);

        Assertions.assertEquals("404 NOT_FOUND \"Video does not exist\"", exception.getMessage());
    }
}