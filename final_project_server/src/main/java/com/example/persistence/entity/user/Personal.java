package com.example.persistence.entity.user;

import com.example.persistence.type.RoleType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PERSONAL")
public class Personal extends User {

    public Personal() {
        super();
        setRoleType(RoleType.PERSONAL);
    }
}
