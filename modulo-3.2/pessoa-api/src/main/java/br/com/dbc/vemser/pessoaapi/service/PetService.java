package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.PetCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.PetDTO;
import br.com.dbc.vemser.pessoaapi.entity.PetEntity;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.PetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;
    private final ObjectMapper objectMapper;

    public List<PetDTO> listarPets() {
        return petRepository.findAll().stream()
                .map(petEntity -> objectMapper.convertValue(petEntity, PetDTO.class))
                .toList();
    }

    public PetDTO cadastrarPet(PetCreateDTO petCreateDTO) {
        PetEntity pet = objectMapper.convertValue(petCreateDTO, PetEntity.class);
        return objectMapper.convertValue(petRepository.save(pet), PetDTO.class);
    }

    public PetDTO listarPetDTOPeloId(Integer idPet) throws RegraDeNegocioException {
        return objectMapper.convertValue(listarPetPeloId(idPet), PetDTO.class);
    }

    public PetEntity listarPetPeloId(Integer idPet) throws RegraDeNegocioException {
        return petRepository.findById(idPet)
                .orElseThrow(() -> new RegraDeNegocioException("Não fo encontrado um Pet com o Id procurado."));
    }

    public PetDTO atualizarPet(Integer idPet, PetCreateDTO petCreateDTO) throws RegraDeNegocioException {
        PetEntity pet = listarPetPeloId(idPet);

        pet.setIdPessoa(petCreateDTO.getIdPessoa());
        pet.setNome(petCreateDTO.getNome());
        pet.setTipoPet(petCreateDTO.getTipoPet());

        PetEntity petSalvo = petRepository.save(pet);

        return objectMapper.convertValue(petSalvo, PetDTO.class);
    }

    public void deletarPet(Integer idPet) throws RegraDeNegocioException {
        PetEntity pet = listarPetPeloId(idPet);
        petRepository.delete(pet);
    }
}