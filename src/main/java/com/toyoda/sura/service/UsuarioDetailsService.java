package com.toyoda.sura.service;

import com.toyoda.sura.entity.Cliente;
import com.toyoda.sura.repository.ClienteRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final ClienteRepository clienteRepository;

    public UsuarioDetailsService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Cliente usuario = clienteRepository.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("User not found with email: " + usuario);
        }
        return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getSenha(),
                new ArrayList<>());
    }
}
