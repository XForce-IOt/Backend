package com.xforce.pethealth.account_management.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xforce.pethealth.account_management.domain.model.aggregates.PetOwner;
import com.xforce.pethealth.account_management.domain.model.commands.AddSubscriptionCommand;
import com.xforce.pethealth.account_management.domain.model.value_objects.Plans;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Subscription extends AbstractAggregateRoot<Subscription> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Plans plans;

    @Column(nullable = false)
    private Float price;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "petOwner_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private PetOwner petOwner;

    protected Subscription() {}

    public Subscription(PetOwner petOwner, AddSubscriptionCommand command) {
        this.petOwner = petOwner;
        this.plans = command.plans();
        this.price = command.price();
    }
}
