package com.xforce.pethealth.account_management.interfaces.rest.resources;

import com.xforce.pethealth.account_management.domain.model.value_objects.Plans;

public record UpdateSubscriptionResource(Long petOwnerId, Long subscriptionId, Plans plans, Float price) {
    public UpdateSubscriptionResource {
        if (petOwnerId == null)
            throw new IllegalArgumentException("petOwnerId cannot be null");
        if (subscriptionId == null)
            throw new IllegalArgumentException("subscriptionId cannot be null");
        if (plans == null)
            throw new IllegalArgumentException("plans cannot be null");
        if (price == null || price <= 0)
            throw new IllegalArgumentException("price cannot be null or less than or equal to 0");
    }
}