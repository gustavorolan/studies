package br.com.cwi.crescer.api.service.finders;

import br.com.cwi.crescer.api.model.Post;
import br.com.cwi.crescer.api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class PostFinderById {
    @Autowired
    private PostRepository postRepository;
    public Post findByIdWithException(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Post não existe"));
    }

}
