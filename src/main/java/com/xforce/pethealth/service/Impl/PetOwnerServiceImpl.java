package com.xforce.pethealth.service.Impl;

import com.xforce.pethealth.dto.PetOwnerDto;
import com.xforce.pethealth.entity.PetOwner;
import com.xforce.pethealth.repository.PetOwnerRepository;
import com.xforce.pethealth.service.PetOwnerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetOwnerServiceImpl implements PetOwnerService {
    private final PetOwnerRepository petOwnerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PetOwnerServiceImpl(PetOwnerRepository petOwnerRepository) {
        this.petOwnerRepository = petOwnerRepository;
        this.modelMapper = new ModelMapper();
    }
    @Override
    public List<PetOwnerDto> getAllPetOwners() {
        return petOwnerRepository.findAll()
                .stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }
    @Override
    public PetOwnerDto createPetOwner(PetOwnerDto petOwnerDto) {
        PetOwner petOwner = DtoToEntity(petOwnerDto);
        return EntityToDto(petOwnerRepository.save(petOwner));
    }
    @Override
    public void updatePetOwner(PetOwnerDto petOwnerDto) {
        PetOwner petOwner = DtoToEntity(petOwnerDto);
        petOwnerRepository.save(petOwner);
    }
    @Override
    public void deletePetOwner(Long id) {
        petOwnerRepository.deleteById(id);
    }
    @Override
    public boolean isPetOwnerExist(Long id) {
        return petOwnerRepository.existsById(id);
    }

    //Sale de nuestro programa a la BD
    private PetOwnerDto EntityToDto(PetOwner petOwner) {
        return modelMapper.map(petOwner, PetOwnerDto.class);
    }
    //Sale de la DB a nuestro programa
    private PetOwner DtoToEntity(PetOwnerDto petOwnerDto) {
        return modelMapper.map(petOwnerDto, PetOwner.class);
    }
}
