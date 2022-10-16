package br.com.cwi.crescer.api.service.finder;

import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.model.VideoType;
import br.com.cwi.crescer.api.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class FindVideoWithThrow {

    @Autowired
    private VideoRepository videoRepository;

    private static final String RESPONSE = "Video does not exist";

    public Video findByIdWithException(Long id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }

    public Video findByIdAndActiveWithException(Long id, Boolean active) {
        return videoRepository.findByIdAndActive(id, active)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }

    public Page<Video> findByActiveAndTypeTWithException(Boolean active, Pageable pageable, VideoType type, String filter) {
        return videoRepository.findByAndActiveAndTypeAndNameContains(active, type, filter, pageable)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }
}
