package com.example.config.security;


import com.example.api.dto.request.auth.RegisterDto;
import com.example.api.dto.response.auth.AuthDto;
import com.example.persistence.entity.token.Token;
import com.example.persistence.entity.user.Personal;
import com.example.persistence.entity.user.User;
import com.example.persistence.repository.token.TokenRepository;
import com.example.persistence.repository.user.PersonalRepository;
import com.example.persistence.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final PersonalRepository personalRepository;
    private final UserRepository<User> userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    // создание нового пользователя но только для персонала-не админа -админ вносится отдельно не путем регистации
    public AuthDto register(RegisterDto dto) {
        if (userRepository.existsByLogin(dto.getEmail())) {
            throw new RuntimeException("This email is already exists");
        }
        Personal personal = new Personal();
        personal.setLogin(dto.getEmail());
        personal.setPassword(passwordEncoder.encode(dto.getPassword()));
        personalRepository.save(personal);
        String jwtToken = jwtService.generateToken(personal);
        Token token = new Token();
        token.setToken(jwtToken);
        token.setUser(personal);
        tokenRepository.save(token);
        return new AuthDto(jwtToken);
    }
    // вход в систему как персонала так и админа-уже с юзером  потому что могут работать и те и те
    public AuthDto login(RegisterDto dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );
        var user = userRepository.findByLogin(dto.getEmail()).orElseThrow();//TODO что добавить сюда в throw чтобы кидало
        String jwtToken = jwtService.generateToken(user);
        Token token = new Token();
        token.setToken(jwtToken);
        token.setUser(user);
        tokenRepository.save(token);
        return new AuthDto(jwtToken);

    }

}
