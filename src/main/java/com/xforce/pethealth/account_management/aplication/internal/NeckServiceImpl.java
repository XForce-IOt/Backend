package com.xforce.pethealth.account_management.aplication.internal;

import com.xforce.pethealth.account_management.domain.model.entities.Neck;
import com.xforce.pethealth.account_management.domain.services.NeckService;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.NeckRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NeckServiceImpl implements NeckService {

    private final NeckRepository neckRepository;
    public NeckServiceImpl(NeckRepository neckRepository) {
        this.neckRepository = neckRepository;
    }

    @Override
    public Neck createNeck(Neck neck) {
        return neckRepository.save(neck);
    }
    @Override
    public void updateNeck(Neck neck) {
        neckRepository.save(neck);
    }
    @Override
    public void deleteNeck(Long id) {
        neckRepository.deleteById(id);
    }
    @Override
    public boolean isNeckExist(Long id) {
        return neckRepository.existsById(id);
    }
    @Override
    public List<Neck> getAllNecks() {
        return neckRepository.findAll();
    }


}
