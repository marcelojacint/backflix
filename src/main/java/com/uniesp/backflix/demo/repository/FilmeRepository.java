package com.uniesp.backflix.demo.repository;

import com.uniesp.backflix.demo.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, UUID> {

    Boolean existsByTitulo(String titulo);


}