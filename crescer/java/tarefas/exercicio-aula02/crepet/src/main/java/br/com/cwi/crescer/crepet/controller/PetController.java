package br.com.cwi.crescer.crepet.controller;

import br.com.cwi.crescer.crepet.model.Pet;
import br.com.cwi.crescer.crepet.repository.PetRepository;
import br.com.cwi.crescer.crepet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public List<Pet> listar() {
        return petService.listar();
    }

    @PostMapping
    public void registerNewPet(@RequestBody Pet pet){
        petService.addNewPet(pet);
    }
}
