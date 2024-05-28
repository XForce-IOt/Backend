package com.xforce.pethealth.service.Impl;

import com.xforce.pethealth.entity.Neck;
import com.xforce.pethealth.repository.NeckRepository;
import com.xforce.pethealth.service.NeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NeckServiceImpl implements NeckService {

    @Autowired
    private NeckRepository neckRepository;

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
    public Neck getNeck(Long id) {
        return neckRepository.findById(id).get();
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
