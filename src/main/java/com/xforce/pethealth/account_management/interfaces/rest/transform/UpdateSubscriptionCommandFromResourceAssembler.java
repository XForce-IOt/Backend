package com.xforce.pethealth.account_management.interfaces.rest.transform;

import com.xforce.pethealth.account_management.domain.model.commands.UpdateSubscriptionCommand;
import com.xforce.pethealth.account_management.interfaces.rest.resources.UpdateSubscriptionResource;

public class UpdateSubscriptionCommandFromResourceAssembler {
    public static UpdateSubscriptionCommand toCommandFromResource(Long petOwnerId, Long subscriptionId, UpdateSubscriptionResource resource) {
        return new UpdateSubscriptionCommand(
                petOwnerId,
                subscriptionId,
                resource.plans(),
                resource.price()
        );
    }
}
