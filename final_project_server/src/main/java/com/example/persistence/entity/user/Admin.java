package com.example.persistence.entity.user;


import com.example.persistence.type.RoleType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {


    public Admin() {
        super();
        setRoleType(RoleType.ADMIN);
        setBalance("NULL");
        setDelivery_info("NULL");
    }

}
