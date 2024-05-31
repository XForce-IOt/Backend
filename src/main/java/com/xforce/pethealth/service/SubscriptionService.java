package com.xforce.pethealth.service;

import com.xforce.pethealth.entity.Subscription;

import java.util.List;

public interface SubscriptionService {
    Subscription createSubscription(Subscription subscription);
    void updateSubscription(Subscription subscription);
    void deleteSubscription(Long id);
    List<Subscription> getAllSubscriptions();
    Subscription getSubscription(Long id);
    boolean isSubscriptionExist(Long id);
}
