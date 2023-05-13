package com.techacademy.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.techacademy.entity.Authentication;
import com.techacademy.repository.AuthenticationRepository;

@Service
public class AuthenticationService {
    private final AuthenticationRepository authenticationRepository;

    public AuthenticationService(AuthenticationRepository repository) {
        this.authenticationRepository = repository;
    }

    /** 全件を検索して返す */
    public List<Authentication> getAuthenticationList() {
        // リポジトリのfindAllメソッドを呼び出す
        return authenticationRepository.findAll();
    }

    /** Authenticationを1件検索して返す */
    public Authentication getAuthentication(String code) {
        return authenticationRepository.findById(code).get();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    /** Authenticationの登録を行なう */
    @Transactional
    public Authentication saveAuthentication(Authentication authentication) {
        authentication.setPassword(passwordEncoder.encode(authentication.getPassword()));
        return authenticationRepository.save(authentication);
    }

}