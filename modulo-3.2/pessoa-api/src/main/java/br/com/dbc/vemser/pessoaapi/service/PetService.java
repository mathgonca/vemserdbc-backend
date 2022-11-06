package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.pet.PetCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.pet.PetDTO;
import br.com.dbc.vemser.pessoaapi.dto.pet.PetUpdateDTO;
import br.com.dbc.vemser.pessoaapi.entity.PessoaEntity;
import br.com.dbc.vemser.pessoaapi.entity.PetEntity;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.PetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;
    private final PessoaService pessoaService;
    private final ObjectMapper objectMapper;

    public PetDTO setPetDTO(PetEntity pet) {
        PetDTO petDTO = objectMapper.convertValue(pet, PetDTO.class);

        Integer idPessoa = pet.getPessoa().getIdPessoa();
        petDTO.setIdPessoa(idPessoa);

        return petDTO;
    }

    public boolean pessoaTemUmPet(Integer idPessoa) {
        Optional<PetEntity> pet = petRepository.findByPessoaIdPessoa(idPessoa);
        return pet.isPresent();
    }

    public List<PetDTO> listarPets() {
        return petRepository.findAll().stream()
                .map(this::setPetDTO)
                .toList();
    }

    public PetDTO cadastrarPet(Integer idPessoa, PetCreateDTO petCreateDTO) throws RegraDeNegocioException {
        PessoaEntity pessoa = pessoaService.listarPessoaPeloId(idPessoa);
        Optional<PetEntity> petOptional = petRepository.findByPessoaIdPessoa(idPessoa);

        if (petOptional.isPresent()) {
            throw new RegraDeNegocioException("Pessoa com esse Id cadastrado já tem um Pet. Operação não concluída");
        }

        PetEntity pet = objectMapper.convertValue(petCreateDTO, PetEntity.class);
        pet.setPessoa(pessoa);
        PetEntity petSalvo = petRepository.save(pet);

        return setPetDTO(petSalvo);
    }

    public PetDTO listarPetDTOPeloId(Integer idPet) throws RegraDeNegocioException {
        PetEntity pet = listarPetPeloId(idPet);
        return setPetDTO(pet);
    }

    public PetEntity listarPetPeloId(Integer idPet) throws RegraDeNegocioException {
        return petRepository.findById(idPet)
                .orElseThrow(() -> new RegraDeNegocioException("Não foi encontrado um Pet com o Id procurado."));
    }

    public PetDTO atualizarPet(Integer idPet, PetUpdateDTO petUpdateDTO) throws RegraDeNegocioException {
        PetEntity pet = listarPetPeloId(idPet);

        Integer idPessoa = petUpdateDTO.getIdPessoa();
        Optional<PetEntity> petOptional = petRepository.findByPessoaIdPessoa(idPessoa);

        if (petOptional.isPresent() && petOptional.get().getIdPet() != idPet) {
            throw new RegraDeNegocioException("Pessoa com esse Id cadastrado já tem um Pet. Operação não concluída");
        }

        PessoaEntity pessoa = pessoaService.listarPessoaPeloId(idPessoa);

        pet.setNome(petUpdateDTO.getNome());
        pet.setTipoPet(petUpdateDTO.getTipoPet());
        pet.setPessoa(pessoa);

        PetEntity petSalvo = petRepository.save(pet);

        return setPetDTO(petSalvo);
    }

    public void deletarPet(Integer idPet) throws RegraDeNegocioException {
        PetEntity pet = listarPetPeloId(idPet);
        petRepository.delete(pet);
    }
}