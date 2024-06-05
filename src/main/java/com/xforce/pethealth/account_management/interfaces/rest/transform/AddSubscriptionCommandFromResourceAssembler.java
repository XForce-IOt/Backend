package com.xforce.pethealth.account_management.interfaces.rest.transform;

import com.xforce.pethealth.account_management.domain.model.commands.AddSubscriptionCommand;
import com.xforce.pethealth.account_management.interfaces.rest.resources.AddSubscriptionResource;

public class AddSubscriptionCommandFromResourceAssembler {
    public static AddSubscriptionCommand toCommandFromResource(Long petOwnerId, AddSubscriptionResource resource) {
        return new AddSubscriptionCommand(
                petOwnerId,
                resource.plans(),
                resource.price()
        );
    }
}
