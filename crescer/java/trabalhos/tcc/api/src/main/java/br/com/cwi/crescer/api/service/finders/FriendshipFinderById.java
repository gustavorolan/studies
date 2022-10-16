package br.com.cwi.crescer.api.service.finders;

import br.com.cwi.crescer.api.model.Friendship;
import br.com.cwi.crescer.api.repository.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class FriendshipFinderById {
    @Autowired
    private FriendshipRepository friendshipRepository;
    public Friendship findByIdWithException(Long id) {
        return friendshipRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Requisiçao não existe"));
    }

}
