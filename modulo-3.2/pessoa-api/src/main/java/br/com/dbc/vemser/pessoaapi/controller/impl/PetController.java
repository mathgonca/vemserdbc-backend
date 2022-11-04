package br.com.dbc.vemser.pessoaapi.controller.impl;

import br.com.dbc.vemser.pessoaapi.dto.PetCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.PetDTO;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pet")
public class PetController {
    private final PetService petService;

    @Operation(summary = "Listar todos os Pets")
    @GetMapping
    public List<PetDTO> listarPets() {
        return petService.listarPets();
    }

    @Operation(summary = "Listar Pet pelo Id")
    @GetMapping("/{idPet}")
    public PetDTO listarPetPeloId(@PathVariable Integer idPet) throws RegraDeNegocioException {
        return petService.listarPetDTOPeloId(idPet);
    }

    @Operation(summary = "Cadastrar Pet")
    @PostMapping("/{idPessoa}")
    public PetDTO cadastrarPet(@PathVariable Integer idPessoa, @RequestBody PetCreateDTO pet) throws RegraDeNegocioException {
        return petService.cadastrarPet(idPessoa, pet);
    }

    @Operation(summary = "Atualizar Pet")
    @PutMapping("/{idPet}")
    public PetDTO atualizarPet(@PathVariable Integer idPet, @RequestBody PetCreateDTO petCreateDTO) throws RegraDeNegocioException {
        return petService.atualizarPet(idPet, petCreateDTO);
    }

    @Operation(summary = "Deletar Pet")
    @DeleteMapping("/{idPet}")
    public void deletarPet(@PathVariable Integer idPet) throws RegraDeNegocioException {
        petService.deletarPet(idPet);
    }
}
