package com.example.demo.config;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (usuarioRepo.count() == 0) {
            usuarioRepo.saveAll(List.of(
                Usuario.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .nombre("Administrador")
                    .rol("ROLE_ADMIN")
                    .activo(true).build(),
                Usuario.builder()
                    .username("bodega")
                    .password(passwordEncoder.encode("bodega123"))
                    .nombre("Encargado Bodega")
                    .rol("ROLE_USER")
                    .activo(true).build()
            ));
            log.info("✅ Usuarios creados: admin / bodega");
        }
    }
}
