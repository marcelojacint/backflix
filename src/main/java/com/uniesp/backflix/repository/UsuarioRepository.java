package com.uniesp.backflix.repository;

import com.uniesp.backflix.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    //method query
    boolean findByEmail(String email);


}
