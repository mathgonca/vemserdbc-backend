package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dto.pet.PetCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.pet.PetDTO;
import br.com.dbc.vemser.pessoaapi.dto.pet.PetUpdateDTO;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<PetDTO> listarPetPeloId(@PathVariable Integer idPet) throws RegraDeNegocioException {
        return new ResponseEntity<>(petService.listarPetDTOPeloId(idPet), HttpStatus.OK);
    }

    @Operation(summary = "Cadastrar Pet")
    @PostMapping("/{idPessoa}")
    public ResponseEntity<PetDTO> cadastrarPet(@PathVariable Integer idPessoa,
                                               @RequestBody @Valid PetCreateDTO pet) throws RegraDeNegocioException {
        return new ResponseEntity<>(petService.cadastrarPet(idPessoa, pet), HttpStatus.OK);
    }

    @Operation(summary = "Atualizar Pet")
    @PutMapping("/{idPet}")
    public ResponseEntity<PetDTO> atualizarPet(@PathVariable Integer idPet,
                                               @RequestBody @Valid PetUpdateDTO petUpdateDTO) throws RegraDeNegocioException {
        return new ResponseEntity<>(petService.atualizarPet(idPet, petUpdateDTO), HttpStatus.OK);
    }

    @Operation(summary = "Deletar Pet")
    @DeleteMapping("/{idPet}")
    public void deletarPet(@PathVariable Integer idPet) throws RegraDeNegocioException {
        petService.deletarPet(idPet);
    }
}
