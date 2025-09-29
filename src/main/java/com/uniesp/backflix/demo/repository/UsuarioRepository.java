package com.uniesp.backflix.demo.repository;

import com.uniesp.backflix.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    //method query
    boolean findByEmail(String email);


}
