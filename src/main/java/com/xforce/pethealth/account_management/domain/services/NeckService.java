package com.xforce.pethealth.account_management.domain.services;

import com.xforce.pethealth.account_management.domain.model.entities.Neck;

import java.util.List;

public interface NeckService {
    Neck createNeck(Neck neck);
    void updateNeck(Neck neck);
    void deleteNeck(Long id);
    List<Neck> getAllNecks();
    boolean isNeckExist(Long id);
}