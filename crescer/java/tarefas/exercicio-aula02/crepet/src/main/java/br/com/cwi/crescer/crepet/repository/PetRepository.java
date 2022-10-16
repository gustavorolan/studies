package br.com.cwi.crescer.crepet.repository;

import br.com.cwi.crescer.crepet.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {


}
