package br.com.cwi.crescer.oldflix.respository;

import br.com.cwi.crescer.oldflix.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {
}
