package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u = repo.findByUsernameAndActivoTrue(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        return new User(u.getUsername(), u.getPassword(),
            List.of(new SimpleGrantedAuthority(u.getRol())));
    }
}
