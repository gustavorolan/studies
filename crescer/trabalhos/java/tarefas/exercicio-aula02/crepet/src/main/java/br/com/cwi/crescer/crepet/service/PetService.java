package br.com.cwi.crescer.crepet.service;

import br.com.cwi.crescer.crepet.model.Pet;
import br.com.cwi.crescer.crepet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public List<Pet> listar() {
        return petRepository.findAll();
    }

    public void addNewPet(Pet pet) {
      petRepository.save(pet);
    }
}
