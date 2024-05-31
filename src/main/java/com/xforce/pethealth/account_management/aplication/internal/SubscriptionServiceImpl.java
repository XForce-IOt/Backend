package com.xforce.pethealth.account_management.aplication.internal;


import com.xforce.pethealth.account_management.domain.model.entities.Subscription;
import com.xforce.pethealth.account_management.domain.services.SubscriptionService;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Override
    public Subscription createSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }
    @Override
    public void updateSubscription(Subscription subscription) {
        subscriptionRepository.save(subscription);
    }
    @Override
    public void deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
    }
    @Override
    public Subscription getSubscription(Long id) {
        return subscriptionRepository.findById(id).get();
    }
    @Override
    public boolean isSubscriptionExist(Long id) {
        return subscriptionRepository.existsById(id);
    }
    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }
}
