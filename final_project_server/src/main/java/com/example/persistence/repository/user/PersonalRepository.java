package com.example.persistence.repository.user;

import com.example.persistence.entity.user.Personal;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRepository extends UserRepository<Personal> {
}
