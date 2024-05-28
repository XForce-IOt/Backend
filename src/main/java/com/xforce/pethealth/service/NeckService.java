package com.xforce.pethealth.service;

import com.xforce.pethealth.entity.Neck;

import java.util.List;

public interface NeckService {
    Neck createNeck(Neck neck);
    void updateNeck(Neck neck);
    void deleteNeck(Long id);
    Neck getNeck(Long id);
    List<Neck> getAllNecks();
    boolean isNeckExist(Long id);
}
