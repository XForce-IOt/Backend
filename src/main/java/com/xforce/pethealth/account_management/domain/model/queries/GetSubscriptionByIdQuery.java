package com.xforce.pethealth.account_management.domain.model.queries;

public record GetSubscriptionByIdQuery(Long petOwnerId, Long subscriptionId) {
    public GetSubscriptionByIdQuery {
        if (petOwnerId == null || petOwnerId <= 0) {
            throw new IllegalArgumentException("Pet owner ID cannot be null");
        }
        if (subscriptionId == null) {
            throw new IllegalArgumentException("Subscription ID cannot be null");
        }
    }
}
