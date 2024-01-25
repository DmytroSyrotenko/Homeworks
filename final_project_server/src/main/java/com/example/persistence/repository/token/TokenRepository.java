package com.example.persistence.repository.token;

import com.example.persistence.entity.token.Token;
import com.example.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends BaseRepository<Token> {

    Optional<Token> findByToken(String token);
    List<Token> findByUserId(Long userId);
}
