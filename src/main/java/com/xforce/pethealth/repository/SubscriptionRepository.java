package com.xforce.pethealth.repository;

import com.xforce.pethealth.entity.PetOwner;
import com.xforce.pethealth.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByPetOwner(PetOwner petOwner);
}
