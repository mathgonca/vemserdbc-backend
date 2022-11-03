package br.com.dbc.vemser.pessoaapi.controller.impl;

import br.com.dbc.vemser.pessoaapi.dto.PetCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.PetDTO;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pet")
public class PetController {
    private final PetService petService;

    @GetMapping
    public List<PetDTO> listarPets() {
        return petService.listarPets();
    }

    @GetMapping("/{idPet}")
    public PetDTO listarPetPeloId(@PathVariable Integer idPet) throws RegraDeNegocioException {
        return petService.listarPetDTOPeloId(idPet);
    }

    @PostMapping
    public PetDTO cadastrarPet(@RequestBody PetCreateDTO pet) {
        return petService.cadastrarPet(pet);
    }

    @PutMapping("/{idPet}")
    public PetDTO atualizarPet(@PathVariable Integer idPet, @RequestBody PetCreateDTO petCreateDTO) throws RegraDeNegocioException {
        return petService.atualizarPet(idPet, petCreateDTO);
    }

    @DeleteMapping("/{idPet}")
    public void deletarPet(@PathVariable Integer idPet) throws RegraDeNegocioException {
        petService.deletarPet(idPet);
    }
}
