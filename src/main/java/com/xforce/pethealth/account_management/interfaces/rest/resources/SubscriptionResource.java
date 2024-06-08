package com.xforce.pethealth.account_management.interfaces.rest.resources;

import com.xforce.pethealth.account_management.domain.model.value_objects.Plans;

public record SubscriptionResource(Long id, Plans plans, Float price) {
}
