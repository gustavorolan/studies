package br.com.cwi.crescer.api.service.finder;

import br.com.cwi.crescer.api.model.Doubt;
import br.com.cwi.crescer.api.repository.DoubtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class FindDoubtWithThrow {

    @Autowired
    private DoubtRepository doubtRepository;

    private static final String RESPONSE = "Doubt does not exist";

    public Doubt findByIdAndActiveWithException(Long id, Boolean active) {
        return doubtRepository.findByIdAndActive(id, active)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }

    public Doubt findByIdWithException(Long id) {
        return doubtRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }

    public Page<Doubt> findByActiveOrderByDateTimeCreationWithException(Boolean active, Pageable pageable) {
        return doubtRepository.findByActiveOrderByDateTimeCreationDesc(active, pageable)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }
}