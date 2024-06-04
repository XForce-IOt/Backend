package com.xforce.pethealth.account_management.domain.services;



import com.xforce.pethealth.account_management.domain.model.entities.Subscription;

import java.util.List;

public interface SubscriptionService {
    Subscription createSubscription(Subscription subscription);
    void updateSubscription(Subscription subscription);
    void deleteSubscription(Long id);
    List<Subscription> getAllSubscriptions();
    Subscription getSubscription(Long id);
    boolean isSubscriptionExist(Long id);
}
