package com.uniesp.backflix.demo.repository;


import com.uniesp.backflix.demo.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {

}
