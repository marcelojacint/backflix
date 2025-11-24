package com.uniesp.backflix.demo.repository;

import com.uniesp.backflix.demo.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

public interface SerieRepository extends JpaRepository<Serie, UUID> {

    Boolean existsByTitulo(String titulo);
}