package com.example.persistence.repository.user;

import com.example.persistence.entity.user.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends UserRepository<Admin> {
}
