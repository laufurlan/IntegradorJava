package com.dh.clinicaSmile.clinica.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

    @Entity
    @Table(name = "usuarios")
    public class Usuario implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_secuencia")
        private Long id;
        private String nombre;
        private String username;
        private String email;
        private String password;

        @Enumerated(EnumType.STRING)
        private UsuarioRol usuarioRol;

        public Usuario() {
        }

        public Usuario(String nombre, String username, String email, String password, UsuarioRol usuarioRol) {
            this.nombre = nombre;
            this.username = username;
            this.email = email;
            this.password = password;
            this.usuarioRol = usuarioRol;
        }

        public Usuario(Long id, String nombre, String username, String email, String password, UsuarioRol usuarioRol) {
            this.id = id;
            this.nombre = nombre;
            this.username = username;
            this.email = email;
            this.password = password;
            this.usuarioRol = usuarioRol;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getUsername() {
            return username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(usuarioRol.name());
            return Collections.singletonList(grantedAuthority);
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public UsuarioRol getUsuarioRol() {
            return usuarioRol;
        }

        public void setUsuarioRol(UsuarioRol usuarioRol) {
            this.usuarioRol = usuarioRol;
        }
    }
